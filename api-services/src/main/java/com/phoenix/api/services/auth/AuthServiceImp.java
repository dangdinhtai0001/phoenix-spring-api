/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:03 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.base.component.exception.DefaultHandlerException;
import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.auth.UserRepository;
import com.phoenix.api.base.services.AbstractBaseService;
import com.phoenix.auth.JwtProvider;
import com.phoenix.time.TimeProvider;
import com.phoenix.time.imp.SystemTimeProvider;
import com.phoenix.util.UUIDFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service(BeanIds.AUTH_SERVICES)
public class AuthServiceImp extends AbstractBaseService {

    private final JwtProvider jwtProvider;
    private final UUIDFactory uuidFactory;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthServiceImp(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider jwtProvider,
            @Qualifier(BeanIds.UUID_Factory) UUIDFactory uuidFactory,
            @Qualifier(BeanIds.DEFAULT_AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager,
            @Qualifier(BeanIds.USER_REPOSITORY) UserRepository userRepository,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities
    ) {
        super(exceptionEntities);
        this.jwtProvider = jwtProvider;
        this.uuidFactory = uuidFactory;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseEntity login(Map loginRequest, HttpSession session) throws DefaultHandlerException {
        try {
            //LinkedHashMap loginRequest = (LinkedHashMap) payload;

            String username = String.valueOf(loginRequest.get("username"));
            String password = String.valueOf(loginRequest.get("password"));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);


            SecurityContextHolder.getContext().setAuthentication(authentication);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Claims claims = new DefaultClaims();
            claims.setSubject(username);

            String accessToken = jwtProvider.generateToken(claims);
            String refreshToken = String.valueOf(uuidFactory.generateRandomUuid());
            TimeProvider timeProvider = new SystemTimeProvider();
            long now = timeProvider.getTime();

            LinkedHashMap<String, String> token = new LinkedHashMap<>();
            token.put("access_token", accessToken);
            token.put("refresh_token", refreshToken);
            token.put("token_type", ApplicationConstant.JWT_TOKEN_TYPE);
            token.put("session_id", session.getId());
            token.put("expires_in", String.valueOf((now + jwtProvider.getTtlMillis())));

            userRepository.updateRefreshTokenByUsername(refreshToken, username);

            return new ResponseEntity(token, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            String code = "AUTH_001";
            throw getDefaultException(code);
        } catch (LockedException e) {
            log.error(e.getMessage());
            String code = "AUTH_002";
            throw getDefaultException(code);
        }
        catch (AccountExpiredException e) {
            log.error(e.getMessage());
            String code = "AUTH_003";
            throw getDefaultException(code);
        }
    }
}

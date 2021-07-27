package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.UserRepositoryImp;
import com.phoenix.api.base.service.AuthService;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.common.auth.JwtProvider;
import com.phoenix.common.time.TimeProvider;
import com.phoenix.common.time.imp.SystemTimeProvider;
import com.phoenix.common.util.UUIDFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service(BeanIds.AUTH_SERVICES)
public class AuthServiceImp extends AbstractBaseService implements AuthService {
    private final JwtProvider jwtProvider;
    private final UUIDFactory uuidFactory;
    private final AuthenticationManager authenticationManager;
    private final UserRepositoryImp userRepository;

    protected AuthServiceImp(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider jwtProvider,
            @Qualifier(BeanIds.UUID_Factory) UUIDFactory uuidFactory,
            @Qualifier(BeanIds.DEFAULT_AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager,
            @Qualifier(BeanIds.USER_REPOSITORY_IMP) UserRepositoryImp userRepository,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities
    ) {
        super(exceptionEntities);

        this.jwtProvider = jwtProvider;
        this.uuidFactory = uuidFactory;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public LinkedHashMap<String, String> login(Map loginRequest, HttpSession session) throws ApplicationException {
        try {
            //LinkedHashMap loginRequest = (LinkedHashMap) payload;

            String username = getPropertyOfRequestBodyByKey(loginRequest, "username");
            String password = getPropertyOfRequestBodyByKey(loginRequest, "password");

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);


            SecurityContextHolder.getContext().setAuthentication(authentication);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return generateToken(username, session);

        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            throw getApplicationException("AUTH_001");
        } catch (LockedException e) {
            log.error(e.getMessage());
            throw getApplicationException("AUTH_002");
        } catch (AccountExpiredException e) {
            log.error(e.getMessage());
            throw getApplicationException("AUTH_003");
        }
    }

    @Override
    public ResponseEntity logout(Map logoutRequest, HttpSession session) {
        return null;
    }

    @Override
    public LinkedHashMap<String, String> refreshToken(Map refreshTokenRequest, HttpSession session) throws ApplicationException {
        String refreshToken = getPropertyOfRequestBodyByKey(refreshTokenRequest, "refresh_token");
        String username = getPropertyOfRequestBodyByKey(refreshTokenRequest, "username");

        if (refreshToken == null || username == null) {
            log.error(("Bad request"));
            throw getApplicationException("COM_001");
        }

        Optional<String> refreshTokenOptional = userRepository.findRefreshTokenByUsername(username);
        String oldRefreshToken = refreshTokenOptional.orElse(null);

        if (oldRefreshToken == null) {
            log.error(String.format("Can't find user with username: %s", username));
            throw getApplicationException("AUTH_001");
        }

        if (refreshToken.equals(oldRefreshToken)) {
            return generateToken(username, session);
        } else {
            log.error(String.format("Invalid JWT refresh token with username: %s", username));
            throw getApplicationException("AUTH_005");
        }
    }

    @Override
    public Optional findProfile(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        return userRepository.findUserProfileByUsername(username);
    }


    //-----------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------

    private LinkedHashMap<String, String> generateToken(String username, HttpSession session) throws ApplicationException {
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

        int result = userRepository.updateRefreshTokenByUsername(refreshToken, username);

        if (result < 0) {
            log.error(String.format("An error occurred while saving the refresh token update for the account: %s", username));
            throw getApplicationException("DB_001");
        }

        return token;
    }
}
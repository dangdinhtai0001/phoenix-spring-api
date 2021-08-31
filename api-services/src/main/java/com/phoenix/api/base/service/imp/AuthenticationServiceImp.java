package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.ApplicationConstant;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.imp.DefaultUserDetailsRepository;
import com.phoenix.api.base.service.AuthenticationService;
import com.phoenix.api.core.config.DefaultExceptionCode;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.common.auth.JwtProvider;
import com.phoenix.common.time.TimeProvider;
import com.phoenix.common.time.imp.SystemTimeProvider;
import com.phoenix.common.util.UUIDFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service(BeanIds.AUTHENTICATION_SERVICES)
public class AuthenticationServiceImp extends AbstractBaseService implements AuthenticationService {
    private final JwtProvider jwtProvider;
    private final UUIDFactory uuidFactory;
    private final AuthenticationManager authenticationManager;
    private final DefaultUserDetailsRepository userRepository;

    protected AuthenticationServiceImp(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider jwtProvider,
            @Qualifier(BeanIds.UUID_FACTORY) UUIDFactory uuidFactory,
            @Qualifier(BeanIds.DEFAULT_AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager,
            @Qualifier(BeanIds.BASE_USER_REPOSITORY_IMP) DefaultUserDetailsRepository userRepository,
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
            throw getApplicationException(DefaultExceptionCode.BAD_CREDENTIALS);
        } catch (LockedException e) {
            log.error(e.getMessage());
            throw getApplicationException(DefaultExceptionCode.ACCOUNT_LOCKED);
        } catch (AccountExpiredException e) {
            log.error(e.getMessage());
            throw getApplicationException(DefaultExceptionCode.ACCOUNT_EXPIRE);
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
            throw getApplicationException(DefaultExceptionCode.BAD_REQUEST);
        }

        Optional<String> refreshTokenOptional = userRepository.findRefreshTokenByUsername(username);
        String oldRefreshToken = refreshTokenOptional.orElse(null);

        if (oldRefreshToken == null) {
            log.error(String.format("Can't find user with username: %s", username));
            throw getApplicationException(DefaultExceptionCode.BAD_CREDENTIALS);
        }

        if (refreshToken.equals(oldRefreshToken)) {
            return generateToken(username, session);
        } else {
            log.error(String.format("Invalid JWT refresh token with username: %s", username));
            throw getApplicationException(DefaultExceptionCode.INVALID_REFRESH_JWT);
        }
    }

    @Override
    public Object findProfile(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();

        return userRepository.findUserPrincipalByUsername(username).orElseGet(null);
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
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);
        token.put("tokenType", ApplicationConstant.JWT_TOKEN_TYPE);
        token.put("sessionId", session.getId());
        token.put("expiresIn", String.valueOf((now + jwtProvider.getTtlMillis())));

        int result = userRepository.updateRefreshTokenByUsername(refreshToken, username);

        if (result < 0) {
            log.error(String.format("An error occurred while saving the refresh token update for the account: %s", username));
            throw getApplicationException(DefaultExceptionCode.DATABASE_ERROR);
        }

        return token;
    }
}

/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:03 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.constant.ApplicationConstant;
import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.model.auth.Token;
import com.phoenix.api.model.payload.LoginRequest;
import com.phoenix.auth.JwtProvider;
import com.phoenix.util.UUIDFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service(BeanIds.AUTH_SERVICES)
public class AuthServiceImp implements AuthService {

    private final JwtProvider jwtProvider;
    private final UUIDFactory uuidFactory;

    public AuthServiceImp(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider jwtProvider,
            @Qualifier(BeanIds.UUID_Factory) UUIDFactory uuidFactory) {
        this.jwtProvider = jwtProvider;
        this.uuidFactory = uuidFactory;
    }

    @Override
    public Token login(Object payload) {
        try {
            LoginRequest loginRequest = (LoginRequest) payload;

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Claims claims = new DefaultClaims();
            claims.setSubject(String.valueOf(principal));

            String accessToken = jwtProvider.generateToken(claims);

            Token token = new Token();


            token.setAccessToken(accessToken);
            token.setIdToken(String.valueOf(uuidFactory.generateRandomUuid()));
            token.setTokenType(ApplicationConstant.JWT_TOKEN_TYPE);
            token.setRefreshToken(String.valueOf(uuidFactory.generateRandomUuid()));
            token.setSessionState("");

            return token;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

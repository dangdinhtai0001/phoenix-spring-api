/*
 * @Author Đặng Đình Tài
 * @Date 6/22/21, 6:03 PM
 */

package com.phoenix.api.services.auth;

import com.phoenix.api.constant.ApplicationConstant;
import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.model.auth.DefaultUserDetails;
import com.phoenix.api.model.auth.Token;
import com.phoenix.auth.JwtProvider;
import com.phoenix.util.UUIDFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Log4j2
@Service(BeanIds.AUTH_SERVICES)
public class AuthServiceImp implements AuthService {

    private final JwtProvider jwtProvider;
    private final UUIDFactory uuidFactory;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImp(
            @Qualifier(BeanIds.JWT_PROVIDER) JwtProvider jwtProvider,
            @Qualifier(BeanIds.UUID_Factory) UUIDFactory uuidFactory,
            @Qualifier(BeanIds.DEFAULT_AUTHENTICATION_MANAGER) AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.uuidFactory = uuidFactory;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity login(Object payload) {
        try {
            LinkedHashMap loginRequest = (LinkedHashMap) payload;

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

            Token token = new Token();

            token.setAccessToken(accessToken);
            token.setIdToken(String.valueOf(uuidFactory.generateRandomUuid()));
            token.setTokenType(ApplicationConstant.JWT_TOKEN_TYPE);
            token.setRefreshToken("");
            token.setSessionState("");

            return new ResponseEntity(token, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.auth;

import com.phoenix.auth.imp.DefaultJwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.Test;

public class JwtProviderTest {

    @Test
    public void testGenerateTokenWithoutPayload(){
        JwtProvider jwtProvider = new DefaultJwtProvider();

        String token = jwtProvider.generateToken(null);

        System.out.println(token);
    }


    @Test
    public void testGenerateTokenClaim(){
        Claims  claims = new DefaultClaims();
        claims.put("claim-key", "claim_value");

        JwtProvider jwtProvider = new DefaultJwtProvider();

        String token = jwtProvider.generateToken(claims);

        System.out.println(token);
    }

}

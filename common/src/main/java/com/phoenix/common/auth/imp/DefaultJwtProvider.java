/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.auth.imp;

import com.phoenix.common.auth.JwtProvider;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Log4j2
public class DefaultJwtProvider implements JwtProvider {
    private String secretKey;
    private SignatureAlgorithm signatureAlgorithm;
    private long ttlMillis;

    public DefaultJwtProvider() {
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
        this.ttlMillis = 60 * 60;
        this.secretKey = "Lorem Ipsum is simply dummy text of the printing and typesetting industry";
    }

    public DefaultJwtProvider(String secretKey, SignatureAlgorithm signatureAlgorithm, long ttlMillis) {
        this.secretKey = secretKey;
        this.signatureAlgorithm = signatureAlgorithm;
        this.ttlMillis = ttlMillis;
    }

    public DefaultJwtProvider(String secretKey, long ttlMillis) {
        this.secretKey = secretKey;
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
        this.ttlMillis = ttlMillis;
    }

    @Override
    public String generateToken(String secretKey, SignatureAlgorithm signatureAlgorithm,
                                String id, String issuer, String subject, long ttlMillis, Map payload) {

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        long nowMillis = System.currentTimeMillis();

        //Create JwtBuilder
        JwtBuilder builder = Jwts.builder();

        //Let's set the JWT Claims
        if (id != null) {
            builder.setId(id);
        }
        if (issuer != null) {
            builder.setId(id);
        }
        if (subject != null) {
            builder.setId(id);
        }
        if (payload != null) {
            builder.setClaims(payload);
        }

        //Sign
        builder.signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    @Override
    public String generateToken(Map payload) {
        return generateToken(this.secretKey, this.signatureAlgorithm, null, null,
                null, this.ttlMillis, payload);
    }

    @Override
    public String generateToken(Claims claims) {
        return generateToken(this.secretKey, this.signatureAlgorithm, null, null,
                null, this.ttlMillis, claims);
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String getIdFromToken(String token) {
        return getClaimsFromToken(token).getId();
    }

    @Override
    public String getIssuerFromToken(String token) {
        return getClaimsFromToken(token).getIssuer();
    }

    @Override
    public String getSubjectFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    @Override
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    @Override
    public void setTtlMillis(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
            throw new ExpiredJwtException(null, null, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT token");
            throw new UnsupportedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims string is empty.");
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
    }

    @Override
    public long getTtlMillis() {
        return ttlMillis;
    }
}

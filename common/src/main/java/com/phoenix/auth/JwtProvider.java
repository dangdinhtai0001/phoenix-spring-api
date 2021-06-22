package com.phoenix.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

public interface JwtProvider {
    String generateToken(String secretKey, SignatureAlgorithm signatureAlgorithm,
                         String id, String issuer, String subject, long ttlMillis, Map payload);

    String generateToken(Map payload);

    String generateToken(Claims claims);

    Claims getClaimsFromToken(String token);

    String getIdFromToken(String token);

    String getIssuerFromToken(String token);

    String getSubjectFromToken(String token);

    void setSecretKey(String secretKey);

    void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm);

    void setTtlMillis(long ttlMillis);

    boolean validateToken(String token);

    long getTtlMillis();
}

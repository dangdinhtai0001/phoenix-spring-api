package com.phoenix.api.base.service;

import com.phoenix.api.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public interface AuthService {
    LinkedHashMap<String, String> login(Map loginRequest, HttpSession session) throws ApplicationException;

    ResponseEntity logout(Map logoutRequest, HttpSession session);

    ResponseEntity refreshToken(Map refreshTokenRequest, HttpSession session);

    Optional findProfile(HttpServletRequest request);
}

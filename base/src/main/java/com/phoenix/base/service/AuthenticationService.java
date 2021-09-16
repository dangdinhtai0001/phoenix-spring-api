package com.phoenix.base.service;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AuthenticationService {
    LinkedHashMap<String, String> login(Map loginRequest, HttpSession session) throws ApplicationException;

    void logout(Map logoutRequest, HttpSession session);

    LinkedHashMap<String, String> refreshToken(Map refreshTokenRequest, HttpSession session) throws ApplicationException;

}

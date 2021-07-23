package com.phoenix.api.base.service;

import com.phoenix.api.core.exception.ServiceException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AuthService {
    LinkedHashMap<String, String> login(Map loginRequest, HttpSession session) throws ServiceException;

    ResponseEntity logout(Map logoutRequest, HttpSession session);

    ResponseEntity refreshToken(Map refreshTokenRequest, HttpSession session);
}

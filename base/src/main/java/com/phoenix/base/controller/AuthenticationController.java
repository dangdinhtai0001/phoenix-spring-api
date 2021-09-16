package com.phoenix.base.controller;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AuthenticationController {

    ResponseEntity login(Map loginRequest, HttpSession session) throws ApplicationException;

    ResponseEntity refreshToken(Map refreshTokenRequest, HttpSession session) throws ApplicationException;

    ResponseEntity logout(Map logoutRequest, HttpSession session) throws ApplicationException;

}


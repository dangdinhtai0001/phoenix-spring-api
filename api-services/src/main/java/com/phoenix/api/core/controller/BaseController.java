package com.phoenix.api.core.controller;

import com.phoenix.api.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

public interface BaseController {
    ResponseEntity handleException(ApplicationException exception);
    ResponseEntity sendResponse(Object response);
}

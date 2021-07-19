package com.phoenix.api.core.controller;

import com.phoenix.api.core.exception.ServiceException;
import org.springframework.http.ResponseEntity;

public interface BaseController {
    ResponseEntity handleException(ServiceException exception);
    ResponseEntity sendResponse(Object response);
}

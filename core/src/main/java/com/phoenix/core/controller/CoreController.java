package com.phoenix.core.controller;

import com.phoenix.core.exception.ApplicationException;
import org.springframework.http.ResponseEntity;

public interface CoreController {
    ResponseEntity handleException(ApplicationException exception);

    ResponseEntity sendResponse(Object response);
}

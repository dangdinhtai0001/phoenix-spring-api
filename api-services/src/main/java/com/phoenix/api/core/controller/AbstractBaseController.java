package com.phoenix.api.core.controller;

import com.phoenix.api.core.exception.ApplicationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The AbstractBaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 *
 * @author Matt Warman from https://github.com/leanstacks/spring-boot-fundamentals/blob/HEAD/src/main/java/org/example/ws/web/api/BaseController.java
 */
public abstract class AbstractBaseController implements BaseController {

    @Override
    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity handleException(ApplicationException exception) {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("code", exception.getCode());
        responseBody.put("message", exception.getMessage());

        if (exception.getHttpStatus() == null) {
            exception.setHttpStatus(HttpStatus.FOUND);
        }
        return new ResponseEntity<>(responseBody, exception.getHttpStatus());
    }

    @Override
    public ResponseEntity sendResponse(Object response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

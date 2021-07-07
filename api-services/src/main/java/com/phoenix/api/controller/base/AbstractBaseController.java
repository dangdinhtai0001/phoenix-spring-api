/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 1:16 PM
 */

package com.phoenix.api.controller.base;

import com.phoenix.api.component.exception.DefaultHandlerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public abstract class AbstractBaseController {

    @ExceptionHandler({DefaultHandlerException.class})
    public ResponseEntity handleException(DefaultHandlerException defaultHandlerException) {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("code", defaultHandlerException.getCode());
//        responseBody.put("resource", defaultHandlerException.getResource());
        responseBody.put("message", defaultHandlerException.getMessage());

        if(defaultHandlerException.getHttpStatus() == null){
            defaultHandlerException.setHttpStatus(HttpStatus.FOUND);
        }
        return new ResponseEntity(responseBody, defaultHandlerException.getHttpStatus());
    }

    public ResponseEntity sendResponse(Object response) {
        return new ResponseEntity(response, HttpStatus.OK);
    }
}

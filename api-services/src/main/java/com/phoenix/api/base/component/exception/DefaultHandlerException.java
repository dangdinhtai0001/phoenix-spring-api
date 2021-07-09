/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 11:53 AM
 */

package com.phoenix.api.base.component.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 */
@Getter
public class DefaultHandlerException extends Exception {
    //========================================================================
    //========== Property
    //========================================================================
    private final String code;
    private final String resource;
    private HttpStatus httpStatus;

    //========================================================================
    //========== Constructor
    //========================================================================
    public DefaultHandlerException(String code, String resource) {
        this.code = code;
        this.resource = resource;
    }

    public DefaultHandlerException(String message, String code, String resource) {
        super(message);
        this.code = code;
        this.resource = resource;
    }

    public DefaultHandlerException(String message, Throwable cause, String code, String resource) {
        super(message, cause);
        this.code = code;
        this.resource = resource;
    }

    public DefaultHandlerException(Throwable cause, String code, String resource) {
        super(cause);
        this.code = code;
        this.resource = resource;
    }

    public DefaultHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.resource = resource;
    }

    public DefaultHandlerException(String code, String resource, HttpStatus httpStatus) {
        this.code = code;
        this.resource = resource;
        this.httpStatus = httpStatus;
    }

    public DefaultHandlerException(String message, String code, String resource, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.resource = resource;
        this.httpStatus = httpStatus;
    }

    public DefaultHandlerException(String message, Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.resource = resource;
        this.httpStatus = httpStatus;
    }

    public DefaultHandlerException(Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(cause);
        this.code = code;
        this.resource = resource;
        this.httpStatus = httpStatus;
    }

    public DefaultHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                                   String code, String resource, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.resource = resource;
        this.httpStatus = httpStatus;
    }

    //========================================================================
    //========== getter
    //========================================================================

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

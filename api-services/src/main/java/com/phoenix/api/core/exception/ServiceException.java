package com.phoenix.api.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ServiceException extends Exception{
    //========================================================================
    //========== Property
    //========================================================================
    private final String code;
    private HttpStatus httpStatus;

    //========================================================================
    //========== Constructor
    //========================================================================
    public ServiceException(String code, String resource) {
        this.code = code;
    }

    public ServiceException(String message, String code, String resource) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, String code, String resource) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause, String code, String resource) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ServiceException(String code, String resource, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String message, String code, String resource, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String message, Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ServiceException(Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                                   String code, String resource, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

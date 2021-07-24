package com.phoenix.api.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception{
    //========================================================================
    //========== Property
    //========================================================================
    private final String code;
    private HttpStatus httpStatus;

    //========================================================================
    //========== Constructor
    //========================================================================
    public ApplicationException(String code, String resource) {
        this.code = code;
    }

    public ApplicationException(String message, String code, String resource) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, String code, String resource) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause, String code, String resource) {
        super(cause);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ApplicationException(String code, String resource, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, String code, String resource, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(Throwable cause, String code, String resource, HttpStatus httpStatus) {
        super(cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
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

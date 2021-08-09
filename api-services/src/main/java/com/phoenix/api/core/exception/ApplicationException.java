package com.phoenix.api.core.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {
    //========================================================================
    //========== Property
    //========================================================================
    private final String code;
    private HttpStatus httpStatus;

    //========================================================================
    //========== Constructor
    //========================================================================
    public ApplicationException(String code) {
        this.code = code;
    }

    public ApplicationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ApplicationException(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, Throwable cause, String code, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(Throwable cause, String code, HttpStatus httpStatus) {
        super(cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                                String code, HttpStatus httpStatus) {
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

package com.phoenix.core.exception;

public class SearchCriteriaException extends Exception {
    public SearchCriteriaException() {
    }

    public SearchCriteriaException(String message) {
        super(message);
    }

    public SearchCriteriaException(String message, Throwable cause) {
        super(message, cause);
    }
}

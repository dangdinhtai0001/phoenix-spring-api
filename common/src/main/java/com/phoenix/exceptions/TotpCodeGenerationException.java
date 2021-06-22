package com.phoenix.exceptions;

public class TotpCodeGenerationException extends Exception{
    public TotpCodeGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}

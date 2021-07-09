/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.exceptions;

public class TotpCodeGenerationException extends Exception{
    public TotpCodeGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}

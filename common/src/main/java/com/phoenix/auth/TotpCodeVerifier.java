/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.auth;

public interface TotpCodeVerifier {
    /**
     * @param secret The shared secret/key to check the code against.
     * @param code The n-digit code given by the end user to check.
     * @return If the code is valid or not.
     */
    boolean isValidCode(String secret, String code);
}

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.auth;

import com.phoenix.exceptions.TotpCodeGenerationException;

public interface TotpCodeGenerator {
    /**
     * @param secret  The shared secret/key to generate the code with.
     * @param counter The current time bucket number. Number of seconds since epoch / bucket period.
     * @return The n-digit code for the secret/counter.
     * @throws TotpCodeGenerationException Thrown if the code generation fails for any reason.
     */
    String generate(String secret, long counter) throws TotpCodeGenerationException;
}

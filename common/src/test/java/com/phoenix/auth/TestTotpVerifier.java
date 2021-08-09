/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.auth;

import com.phoenix.common.auth.TotpCodeGenerator;
import com.phoenix.common.auth.imp.DefaultTotpCodeGenerator;
import com.phoenix.common.auth.imp.DefaultTotpCodeVerifier;
import com.phoenix.common.exceptions.TotpCodeGenerationException;
import com.phoenix.common.time.TimeProvider;
import com.phoenix.common.time.imp.SystemTimeProvider;
import org.junit.Test;

public class TestTotpVerifier {

    @Test
    public void testVerification() throws TotpCodeGenerationException {
        String secret = "EX47GINFPBK5GNLYLILGD2H6ZLGJNNWB";
        int period = 30;

        TotpCodeGenerator generator = new DefaultTotpCodeGenerator();
        TimeProvider timeProvider = new SystemTimeProvider();
        DefaultTotpCodeVerifier verifier = new DefaultTotpCodeVerifier(generator, timeProvider);
        verifier.setTimePeriod(period);

        long currentBucket = Math.floorDiv(new SystemTimeProvider().getTime(), period);
        String code = generator.generate(secret, currentBucket);

        System.out.println(generator.generate(secret,currentBucket));
        System.out.println(verifier.isValidCode(secret,code));

    }


}

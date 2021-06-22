package com.phoenix.auth;

import com.phoenix.auth.imp.DefaultTotpCodeGenerator;
import com.phoenix.exceptions.TotpCodeGenerationException;
import com.phoenix.time.imp.SystemTimeProvider;
import org.junit.Test;

public class TestTotpCodeGenerator {
    @Test
    public void testGenerate() throws TotpCodeGenerationException {
        long currentBucket = Math.floorDiv(new SystemTimeProvider().getTime(), 30);
        TotpCodeGenerator totpCodeGenerator = new DefaultTotpCodeGenerator();

        String res = totpCodeGenerator.generate("123", currentBucket);

        System.out.println(res);
    }

    /**
     * Java Right Shift Operator
     * The Java right shift operator >> is used to move left operands value to right by the number of bits specified by
     * the right operand.
     */
    @Test
    public void testRightShiftOperator() {
        System.out.println(10 >> 2);//10/2^2=10/4=2
        System.out.println(20 >> 2);//20/2^2=20/4=5
        System.out.println(20 >> 3);//20/2^3=20/8=2
    }
}

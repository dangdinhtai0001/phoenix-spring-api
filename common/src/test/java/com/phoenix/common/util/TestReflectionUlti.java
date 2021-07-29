package com.phoenix.common.util;

import com.phoenix.common.auth.imp.DefaultJwtProvider;
import com.phoenix.common.structure.Pair;
import org.junit.Test;

import java.util.List;

public class TestReflectionUlti {
    @Test
    public void testGetFieldAsPairList(){
        List<Pair<String,Class>> list = ReflectionUtil.getFieldAsPairList(DefaultJwtProvider.class, "secretKey", "signatureAlgorithm", "ttlMillis");

        System.out.println(list);
    }
}

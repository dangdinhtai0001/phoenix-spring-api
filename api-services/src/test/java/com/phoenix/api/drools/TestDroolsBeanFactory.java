/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/14/21, 5:17 PM
 */

package com.phoenix.api.drools;


import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class TestDroolsBeanFactory {

    @Test
    public void testGetAllRulesFilePath() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
        Resource[] resources = resolver.getResources("classpath*:/drools/rules/**.drl") ;

        for (Resource resource: resources){
            System.out.println(resource.getFilename());
        }
    }


}

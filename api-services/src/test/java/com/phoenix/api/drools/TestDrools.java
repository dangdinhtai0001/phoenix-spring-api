package com.phoenix.api.drools;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.business.drools.DroolsFactory;
import org.junit.jupiter.api.Test;
import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
public class TestDrools {

    @Autowired
    @Qualifier(BeanIds.DROOLS_FACTORY)
    private DroolsFactory droolsFactory;

    @Test
    public void testApplyRule() {
        Resource resource = ResourceFactory.newClassPathResource("drools/rules/authentication/Authentication.drl", getClass());
        droolsFactory.getKieSession(resource);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }
}

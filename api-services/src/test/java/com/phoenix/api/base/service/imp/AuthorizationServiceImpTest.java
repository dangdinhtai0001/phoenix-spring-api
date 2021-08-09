package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.service.AuthorizationService;
import org.casbin.jcasbin.model.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class AuthorizationServiceImpTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private Model model;

    @Test
    public void testLoadPolicies() throws Exception {
        authorizationService.loadPolicies(model);

    }

    @Test
    public void testEnforce() throws Exception {
        authorizationService.clearPolicies(model);
        authorizationService.loadPolicies(model);
        String[] enforceArgs = new String[]{"user", "UserServiceImp", "countByCondition"};
        boolean isAllow = authorizationService.enforce(model, enforceArgs);

        System.out.println(isAllow);

    }

}
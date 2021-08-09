package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.service.AuthorizationService;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class AuthorizationServiceImpTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private Enforcer enforcer;

    @Test
    public void testLoadPolicies() throws Exception {
//        authorizationService.loadPolicies(model);

    }

    @Test
    public void testEnforce() throws Exception {
        authorizationService.clearPolicies(enforcer);
        authorizationService.loadPolicies(enforcer);


//        enforcer.addPolicy("TEACHER", "UserServiceImp", "countByCondition");
//        enforcer.addPolicy("TEACHER", "UserServiceImp", "findByCondition");
//        enforcer.addGroupingPolicy("student_01", "STUDENT");
//        enforcer.addGroupingPolicy("student_02", "STUDENT");
//        enforcer.addGroupingPolicy("admin", "TEACHER");
////        enforcer.addGroupingPolicy("teacher_01", "TEACHER");
//        enforcer.addGroupingPolicy(new String[]{"teacher_01", "TEACHER"});

        String[] enforceArgs = new String[]{"admin", "UserServiceImp", "countByCondition"};
        boolean isAllow = authorizationService.enforce(enforcer, enforceArgs);

        System.out.println(isAllow);

    }

}
package com.phoenix.api.base.casbin;

import org.casbin.jcasbin.main.Enforcer;

public class TestCasbin {
    public void initEnforce(){
        Enforcer enforcer = new Enforcer();

        enforcer.addPolicy("alice", "data1", "read");

    }
}

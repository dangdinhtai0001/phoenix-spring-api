package com.phoenix.base.service;

import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;

public interface AuthorizationService {
    Model loadModelFromPath(String path);

    void loadPolicies(Enforcer enforcer);

    void clearPolicies(Enforcer enforcer);

    boolean enforce(Enforcer enforcer, Object... args);
}

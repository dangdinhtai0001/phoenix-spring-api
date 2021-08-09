package com.phoenix.api.base.service;

import org.casbin.jcasbin.model.Model;

public interface AuthorizationService {
    Model loadModelFromPath(String path);

    void loadPolicies(Model model);

    boolean enforce(Model model, String... args);

}

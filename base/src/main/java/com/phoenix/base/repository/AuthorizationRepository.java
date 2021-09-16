package com.phoenix.base.repository;

import com.phoenix.base.model.CasbinRule;

import java.util.List;

public interface AuthorizationRepository {
    List<CasbinRule> findAllCasbinRules();
}

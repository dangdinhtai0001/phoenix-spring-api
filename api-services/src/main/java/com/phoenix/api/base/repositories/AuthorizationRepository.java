package com.phoenix.api.base.repositories;

import com.phoenix.api.base.model.CasbinRule;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AuthorizationRepository {
    List<CasbinRule> findAllCasbinRules() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

}

package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.CasbinRule;
import com.phoenix.api.base.repositories.AuthorizationRepository;
import com.phoenix.api.core.repository.AbstractNativeRepository;
import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository(BeanIds.AUTHORIZATION_REPOSITORY_IMP)
public class AuthorizationRepositoryImp extends AbstractNativeRepository implements AuthorizationRepository {
    public AuthorizationRepositoryImp(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public List<CasbinRule> findAllCasbinRules() throws NoSuchFieldException, InvocationTargetException,
            IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql = "select type, arg_1, arg_2, arg_3 from fw_resource_policies";

        List<Object[]> queryResult = executeNativeQuery(sql);

        List<Pair<String, Class>> params = ReflectionUtil.getFieldAsPairList(CasbinRule.class, "pType", "arg1", "arg2", "arg3");

        return parseResult(queryResult, params, CasbinRule.class);
    }
}

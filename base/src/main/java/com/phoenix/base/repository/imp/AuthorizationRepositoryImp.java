package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.CasbinRule;
import com.phoenix.base.repository.AuthorizationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.AUTHORIZATION_REPOSITORY_IMP)
@Log4j2
public class AuthorizationRepositoryImp implements AuthorizationRepository {
    @Override
    public List<CasbinRule> findAllCasbinRules() {
        return null;
    }
}

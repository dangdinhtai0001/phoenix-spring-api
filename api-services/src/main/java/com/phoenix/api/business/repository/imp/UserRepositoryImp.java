package com.phoenix.api.business.repository.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.core.repository.AbstractNativeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.USER_REPOSITORY_IMP)
public class UserRepositoryImp extends AbstractNativeRepository implements UserRepository {

    public UserRepositoryImp(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public long countByCondition(String condition) {
        String sql = "select count(*) from profile p right join fw_user fu on fu.id = p.user_id" + condition;
        Object queryResult = executeNativeQuery(sql).get(0);

        return Long.parseLong(String.valueOf(queryResult));
    }
}

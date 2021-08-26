package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.repository.AbstractBaseJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.EXCEPTION_REPOSITORY_IMP)
public class ExceptionRepositoryImp extends AbstractBaseJpaRepository<ExceptionEntity, Long> {

    public ExceptionRepositoryImp(EntityManager entityManager) {
        super(entityManager, ExceptionEntity.class);
    }
}

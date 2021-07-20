package com.phoenix.api.base.repositories;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.repository2.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(BeanIds.EXCEPTION_REPOSITORY_IMP)
public class ExceptionRepositoryImp extends AbstractBaseRepository<ExceptionEntity, Long> {
    @PersistenceContext
    private final EntityManager entityManager;

    public ExceptionRepositoryImp(EntityManager entityManager) {
        super(entityManager, ExceptionEntity.class);
        this.entityManager = entityManager;
    }
}

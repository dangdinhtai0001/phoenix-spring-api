package com.phoenix.api.repositories.auth;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.base.repositories.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.PERMISSION_REPOSITORY_IMP)
public class PermissionRepositoryImp extends AbstractRepository<PermissionEntity> {

    public PermissionRepositoryImp(EntityManager entityManager) {
        super(entityManager, PermissionEntity.class);
    }
}

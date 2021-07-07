package com.phoenix.api.repositories.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.base.AbstractBaseRepository;
import com.phoenix.api.repositories.base.AbstractRepository;
import com.phoenix.api.services.base.AbstractBaseService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository(BeanIds.PERMISSION_REPOSITORY_IMP)
public class PermissionRepositoryImp extends AbstractRepository<PermissionEntity> {

    public PermissionRepositoryImp(EntityManager entityManager) {
        super(entityManager, PermissionEntity.class);
    }
}

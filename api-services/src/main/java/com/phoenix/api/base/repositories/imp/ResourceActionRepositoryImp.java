package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ResourceAction;
import com.phoenix.api.base.repositories.ResourceActionRepository;
import com.phoenix.api.core.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.RESOURCE_ACTION_REPOSITORY_IMP)
public class ResourceActionRepositoryImp extends AbstractBaseRepository<ResourceAction, Long> implements ResourceActionRepository {
    public ResourceActionRepositoryImp(EntityManager entityManager) {
        super(entityManager, ResourceAction.class);
    }
}

package com.phoenix.api.repositories.common;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.repositories.base.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.MENU_REPOSITORY_IMP)
public class MenuRepositoryImp extends AbstractRepository<MenuEntity> {
    public MenuRepositoryImp(EntityManager entityManager) {
        super(entityManager, MenuEntity.class);
    }
}

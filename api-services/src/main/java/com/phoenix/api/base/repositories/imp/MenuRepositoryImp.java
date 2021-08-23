package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.MenuEntity;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.core.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.MENU_REPOSITORY_IMP)
public class MenuRepositoryImp extends AbstractBaseRepository<MenuEntity, Long> implements MenuRepository {
    public MenuRepositoryImp(EntityManager entityManager) {
        super(entityManager, MenuEntity.class);
    }
}

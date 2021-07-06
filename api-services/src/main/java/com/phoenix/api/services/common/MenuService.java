package com.phoenix.api.services.common;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.repositories.base.AbstractRepository;
import com.phoenix.api.services.base.AbstractCrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.MENU_SERVICES)
public class MenuService extends AbstractCrudService<MenuEntity> {
    protected MenuService(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.MENU_REPOSITORY_IMP) AbstractRepository<MenuEntity> repository) {
        super(exceptionEntities, repository, MenuEntity.class);
    }

    @Override
    public void preAdd(MenuEntity object) {
        System.out.println("Pre add");
    }

    @Override
    public void preUpdate(MenuEntity object) {
        System.out.println("Pre update");

    }

    @Override
    public void preRemove(MenuEntity object) {
        System.out.println("Pre remove");

    }

    @Override
    public void preFindById(Long id) {
        System.out.println("Pre find by id");
    }

    @Override
    public void afterAdd(MenuEntity object) {
        System.out.println("after add");

    }

    @Override
    public void afterUpdate(MenuEntity object) {
        System.out.println("after update");

    }

    @Override
    public void afterRemove(MenuEntity object) {
        System.out.println("after remove");

    }
}

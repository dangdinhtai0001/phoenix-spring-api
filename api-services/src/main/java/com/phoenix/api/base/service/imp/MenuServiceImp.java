package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.base.service.MenuService;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.service.AbstractBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.MENU_SERVICES)
@Log4j2
public class MenuServiceImp extends AbstractBaseService implements MenuService {
    private final MenuRepository menuRepository;

    protected MenuServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.MENU_REPOSITORY_IMP) MenuRepository menuRepository
    ) {
        super(exceptionEntities);
        this.menuRepository = menuRepository;
    }

    @Override
    public List findAll() {
        return menuRepository.findAll();
    }
}

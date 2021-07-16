/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.api.services.common;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.base.services.AbstractCrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(BeanIds.MENU_SERVICES)
public class MenuService extends AbstractCrudService<MenuEntity> {
    protected MenuService(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.MENU_REPOSITORY_IMP) AbstractRepository<MenuEntity> repository) {
        super(exceptionEntities, repository, MenuEntity.class);
    }
}

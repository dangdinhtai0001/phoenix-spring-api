/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/6/21, 8:31 PM
 */

package com.phoenix.api.controller.common;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.controller.AbstractCrudController;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.base.services.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(BeanIds.MENU_CONTROLLER)
@RequestMapping(value = "/menu")
public class MenuController extends AbstractCrudController<MenuEntity> {
    protected MenuController(CrudService<MenuEntity> service) {
        super(service);
    }
}

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/28/21, 10:12 PM
 */

package com.phoenix.api.controller;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.controller.base.AbstractCrudController;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.services.ProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/profile")
@RestController(value = BeanIds.PROFILE_CONTROLLER)
public class ProfileController extends AbstractCrudController<ProfileEntity> {
    protected ProfileController(
            @Qualifier(BeanIds.PROFILE_SERVICE) ProfileService service) {
        super(service);
    }
}

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/28/21, 10:12 PM
 */

package com.phoenix.api.controller;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.controller.base.CrudController;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.services.ProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/profile")
@Controller
public class ProfileController extends CrudController<ProfileEntity> {
    protected ProfileController(
            @Qualifier(BeanIds.PROFILE_SERVICE) ProfileService service) {
        super(service);
    }
}

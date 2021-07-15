package com.phoenix.api.controller;

import com.phoenix.api.base.controller.AbstractCrudController;
import com.phoenix.api.base.services.CrudService;
import com.phoenix.api.entities.ProfileEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ProfileControllerImp")
@RequestMapping(value = "/profile")
public class ProfileControllerImp extends AbstractCrudController<ProfileEntity> {
    protected ProfileControllerImp(CrudService<ProfileEntity> service) {
        super(service);
    }
}

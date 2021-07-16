package com.phoenix.api.controller;

import com.phoenix.api.base.controller.AbstractCrudController;
import com.phoenix.api.base.services.CrudService;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.services.ProfileServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ProfileControllerImp")
@RequestMapping(value = "/profile")
public class ProfileControllerImp extends AbstractCrudController<ProfileEntity> {
    private final ProfileServiceImp profileServiceImp;

    protected ProfileControllerImp(CrudService<ProfileEntity> service) {
        super(service);
        profileServiceImp = (ProfileServiceImp) service;
    }

    @Override
    public ResponseEntity findAll() {
        return sendResponse(profileServiceImp.findAll(""));
    }
}

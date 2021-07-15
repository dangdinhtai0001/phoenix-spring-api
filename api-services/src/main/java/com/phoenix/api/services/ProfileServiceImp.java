package com.phoenix.api.services;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.base.services.AbstractCrudService;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProfileServiceImp")
public class ProfileServiceImp extends AbstractCrudService<ProfileEntity> {
    protected ProfileServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier("ProfileRepositoryImp") AbstractRepository<ProfileEntity> repository) {
        super(exceptionEntities, repository, ProfileEntity.class);
    }

    @Override
    public void preAdd(ProfileEntity object) {

    }

    @Override
    public void preUpdate(ProfileEntity object) {

    }

    @Override
    public void preRemove(ProfileEntity object) {

    }

    @Override
    public void afterAdd(ProfileEntity object) {

    }

    @Override
    public void afterUpdate(ProfileEntity object) {

    }

    @Override
    public void afterRemove(ProfileEntity object) {

    }
}

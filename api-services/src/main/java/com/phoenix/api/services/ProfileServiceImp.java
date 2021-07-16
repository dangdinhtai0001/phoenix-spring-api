package com.phoenix.api.services;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.base.services.AbstractCrudService;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.ProfileRepositoryImp;
import com.phoenix.business.domain.Profile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("ProfileServiceImp")
public class ProfileServiceImp extends AbstractCrudService<ProfileEntity> {
    private ProfileRepositoryImp repositoryImp;

    protected ProfileServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier("ProfileRepositoryImp") AbstractRepository<ProfileEntity> repository) {
        super(exceptionEntities, repository, ProfileEntity.class);
        this.repositoryImp = (ProfileRepositoryImp) repository;
    }

    public Iterable<Profile> findAll()  {
        try {
            return this.repositoryImp.findAllProfile();
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}

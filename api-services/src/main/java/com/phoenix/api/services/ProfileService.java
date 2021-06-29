/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/28/21, 10:08 PM
 */

package com.phoenix.api.services;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.ProfileRepositoryImp;
import com.phoenix.api.services.base.CrudAbstractService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = BeanIds.PROFILE_SERVICE)
public class ProfileService extends CrudAbstractService<ProfileEntity> {
    protected ProfileService(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.PROFILE_REPOSITORY_IMP) ProfileRepositoryImp repository
    ) {
        super(exceptionEntities, repository, ProfileEntity.class);
    }

    @Override
    public ProfileEntity preAdd(ProfileEntity object) {
        System.out.println("Pre add");
        return object;
    }

    @Override
    public ProfileEntity preUpdate(ProfileEntity object) {
        return object;
    }

    @Override
    public ProfileEntity preRemove(ProfileEntity object) {
        return object;
    }

    @Override
    public ProfileEntity afterAdd(ProfileEntity object) {
        return object;
    }

    @Override
    public ProfileEntity afterUpdate(ProfileEntity object) {
        return object;
    }

    @Override
    public ProfileEntity afterRemove(ProfileEntity object) {
        return object;
    }
}

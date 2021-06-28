/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/28/21, 10:03 PM
 */

package com.phoenix.api.repositories;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.api.repositories.base.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(value = BeanIds.PROFILE_REPOSITORY_IMP)
public class ProfileRepositoryImp extends AbstractRepository<ProfileEntity> {
    public ProfileRepositoryImp(EntityManager entityManager) {
        super(entityManager, ProfileEntity.class);
    }
}

/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 10:38 AM
 */

package com.phoenix.api.repositories.common;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.base.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.EXCEPTION_REPOSITORY_IMP)
public class ExceptionRepositoryImp extends AbstractRepository<ExceptionEntity> {

    public ExceptionRepositoryImp(EntityManager entityManager) {
        super(entityManager,ExceptionEntity.class);
    }
}
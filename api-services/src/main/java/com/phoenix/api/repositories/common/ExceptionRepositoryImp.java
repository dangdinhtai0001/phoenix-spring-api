/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 10:38 AM
 */

package com.phoenix.api.repositories.common;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.base.repositories.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.EXCEPTION_REPOSITORY_IMP)
public class ExceptionRepositoryImp extends AbstractRepository<ExceptionEntity> {

    public ExceptionRepositoryImp(EntityManager entityManager) {
        super(entityManager,ExceptionEntity.class);
    }
}

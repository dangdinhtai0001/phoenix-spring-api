/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 12:46 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ExceptionRepo extends AbstractRepository<ExceptionEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    public ExceptionRepo(EntityManager entityManager) {
        super(entityManager, ExceptionEntity.class);
    }


}

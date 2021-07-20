package com.phoenix.api.core.repository2;

import com.phoenix.api.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class AbstractBaseRepository<T extends BaseEntity<ID>, ID extends Serializable> {

    private final SimpleJpaRepository<T, ID> jpaRepository;
    private final EntityManager entityManager;
    private final Class<T> typeParameterClass;

    public AbstractBaseRepository(EntityManager entityManager, Class<T> typeParameterClass) {
        this.entityManager = entityManager;
        this.typeParameterClass = typeParameterClass;
        jpaRepository = new SimpleJpaRepository<>(typeParameterClass, entityManager);
    }

    public SimpleJpaRepository<T, ID> getJpaRepository() {
        return jpaRepository;
    }


}

package com.phoenix.api.core.repository2;

import com.phoenix.api.core.entity.BaseEntity;

import javax.persistence.EntityManager;

public abstract class AbstractBaseRepository<T extends BaseEntity<ID>, ID> implements BaseRepository<T, ID> {
    private EntityManager entityManager;
}

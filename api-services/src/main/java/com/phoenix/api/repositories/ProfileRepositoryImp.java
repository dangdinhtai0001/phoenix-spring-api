package com.phoenix.api.repositories;

import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.business.domain.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("ProfileRepositoryImp")
public class ProfileRepositoryImp extends AbstractRepository<ProfileEntity> {
    public ProfileRepositoryImp(EntityManager entityManager) {
        super(entityManager, ProfileEntity.class);
    }

    public Iterable<Profile> findAllProfile() {
        return null;
    }
}

package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.FilterMetadata;
import com.phoenix.api.base.repositories.FilterMetadataRepository;
import com.phoenix.api.core.repository.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository(BeanIds.FILTER_METADATA_REPOSITORY_IMP)
public class FilterMetadataRepositoryImp extends AbstractBaseRepository<FilterMetadata, Long> implements FilterMetadataRepository {

    public FilterMetadataRepositoryImp(EntityManager entityManager) {
        super(entityManager, FilterMetadata.class);
    }
}

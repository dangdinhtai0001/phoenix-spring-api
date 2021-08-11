package com.phoenix.api.base.repositories;

import com.phoenix.api.base.entities.FilterMetadataEntity;
import com.phoenix.api.core.repository.BaseRepository;

import java.util.List;

public interface FilterMetadataRepository extends BaseRepository<FilterMetadataEntity, Long> {
    List<FilterMetadataEntity> findByObject(String object);

}

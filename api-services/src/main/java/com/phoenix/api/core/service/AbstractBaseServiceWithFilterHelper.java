package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.FilterMetadataEntity;
import com.phoenix.api.base.repositories.FilterMetadataRepository;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.common.util.TextUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractBaseServiceWithFilterHelper extends AbstractBaseService implements BaseServiceWithFilterHelper {
    private final FilterMetadataRepository filterMetadataRepository;

    protected AbstractBaseServiceWithFilterHelper(List<ExceptionEntity> exceptionEntities, FilterMetadataRepository filterMetadataRepository) {
        super(exceptionEntities);
        this.filterMetadataRepository = filterMetadataRepository;
    }

    @Override
    public List<SearchCriteria> mapBusinessObject2Table(List<SearchCriteria> searchCriteriaList, Class businessClass) throws SearchCriteriaException {
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return searchCriteriaList;
        }
        String className = businessClass.getName();

        List<FilterMetadataEntity> filterMetadataList = filterMetadataRepository.findByObject(className);

        return mapBusinessObject2Table(searchCriteriaList, filterMetadataList);
    }

    @Override
    public List<SearchCriteria> mapBusinessObject2Table(List<SearchCriteria> searchCriteriaList, List<FilterMetadataEntity> filterMetadataList) throws SearchCriteriaException {
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return searchCriteriaList;
        }

        Map<String, FilterMetadataEntity> map = filterMetadataList.stream()
                .collect(Collectors.toMap(FilterMetadataEntity::getField, filterMetadataEntity -> filterMetadataEntity));

        FilterMetadataEntity filterMetadataEntity;
        for (SearchCriteria searchCriteria : searchCriteriaList) {
            filterMetadataEntity = map.getOrDefault(searchCriteria.getKey(), null);

            if (filterMetadataEntity == null
                    || TextUtil.isNullOrEmpty(filterMetadataEntity.getTable())
                    || TextUtil.isNullOrEmpty(filterMetadataEntity.getColumn()
            )) {
                throw new SearchCriteriaException(String.format("Missing metadata about the field '%s'", searchCriteria.getKey()));
            }

            if (filterMetadataEntity.getAlias() == null || filterMetadataEntity.getAlias().isEmpty()) {
                searchCriteria.setKey(String.format("%s.%s", filterMetadataEntity.getTable(), filterMetadataEntity.getColumn()));
            } else {
                searchCriteria.setKey(String.format("%s.%s", filterMetadataEntity.getAlias(), filterMetadataEntity.getColumn()));
            }
        }
        return searchCriteriaList;
    }
}

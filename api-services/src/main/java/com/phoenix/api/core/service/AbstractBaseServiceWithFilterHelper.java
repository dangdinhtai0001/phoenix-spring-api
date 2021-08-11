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
    public List<SearchCriteria> mapBusinessObjectSearchCriteria2Table(List<SearchCriteria> searchCriteriaList, Class businessClass) throws SearchCriteriaException {
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return searchCriteriaList;
        }
        Map<String, FilterMetadataEntity> filterMetadataMap = findFilterMetadataByBusinessClass(businessClass);

        return mapBusinessObjectSearchCriteria2Table(searchCriteriaList, filterMetadataMap);
    }

    @Override
    public List<SearchCriteria> mapBusinessObjectSearchCriteria2Table(List<SearchCriteria> searchCriteriaList, Map<String, FilterMetadataEntity> filterMetadataMap) throws SearchCriteriaException {
        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return searchCriteriaList;
        }


        FilterMetadataEntity filterMetadataEntity;
        for (SearchCriteria searchCriteria : searchCriteriaList) {
            filterMetadataEntity = filterMetadataMap.getOrDefault(searchCriteria.getKey(), null);

            searchCriteria.setKey(updateBusinessKeys(filterMetadataEntity, searchCriteria.getKey()));
        }
        return searchCriteriaList;
    }

    @Override
    public List<String> mapBusinessObjectField2TableColumn(List<String> fieldNames, Map<String, FilterMetadataEntity> filterMetadataMap) throws SearchCriteriaException {
        if (fieldNames == null || fieldNames.isEmpty()) {
            return fieldNames;
        }

        FilterMetadataEntity filterMetadataEntity;

        for (int i = 0; i < fieldNames.size(); i++) {
            filterMetadataEntity = filterMetadataMap.getOrDefault(fieldNames.get(i), null);

            fieldNames.set(i, updateBusinessKeys(filterMetadataEntity, fieldNames.get(i)));
        }

        return fieldNames;
    }

    @Override
    public List<String> mapBusinessObjectField2TableColumn(List<String> fieldNames, Class businessClass) throws SearchCriteriaException {
        if (fieldNames == null || fieldNames.isEmpty()) {
            return fieldNames;
        }

        Map<String, FilterMetadataEntity> filterMetadataMap = findFilterMetadataByBusinessClass(businessClass);

        return mapBusinessObjectField2TableColumn(fieldNames, filterMetadataMap);
    }

    //************************************************************************************************************************************
    //***************
    //************************************************************************************************************************************


    private String updateBusinessKeys(FilterMetadataEntity filterMetadataEntity, String key) throws SearchCriteriaException {
        if (filterMetadataEntity == null
                || TextUtil.isNullOrEmpty(filterMetadataEntity.getTable())
                || TextUtil.isNullOrEmpty(filterMetadataEntity.getColumn()
        )) {
            throw new SearchCriteriaException(String.format("Missing metadata about the field '%s'", key));
        }

        if (filterMetadataEntity.getAlias() == null || filterMetadataEntity.getAlias().isEmpty()) {
            return (String.format("%s.%s", filterMetadataEntity.getTable(), filterMetadataEntity.getColumn()));
        } else {
            return (String.format("%s.%s", filterMetadataEntity.getAlias(), filterMetadataEntity.getColumn()));
        }
    }

    private Map<String, FilterMetadataEntity> findFilterMetadataByBusinessClass(Class businessClass) {
        String className = businessClass.getName();

        List<FilterMetadataEntity> filterMetadataList = filterMetadataRepository.findByObject(className);

        return filterMetadataList.stream()
                .collect(Collectors.toMap(FilterMetadataEntity::getField, filterMetadataEntity -> filterMetadataEntity));
    }
}

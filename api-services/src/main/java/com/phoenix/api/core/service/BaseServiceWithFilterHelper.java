package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.FilterMetadataEntity;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;

import java.util.List;

public interface BaseServiceWithFilterHelper extends BaseService{

    List<SearchCriteria> mapBusinessObject2Table(List<SearchCriteria> searchCriteriaList, Class businessClass) throws SearchCriteriaException;

    List<SearchCriteria> mapBusinessObject2Table(List<SearchCriteria> searchCriteriaList, List<FilterMetadataEntity> filterMetadataList) throws SearchCriteriaException;
}

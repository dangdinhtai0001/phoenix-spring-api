package com.phoenix.api.core.service;

import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;

import java.util.List;
import java.util.Map;

public interface BaseServiceWithFilterHelper extends BaseService{

    List<SearchCriteria> mapBusinessObjectSearchCriteria2Table(List<SearchCriteria> searchCriteriaList, Class businessClass) throws SearchCriteriaException;

    List<String> mapBusinessObjectField2TableColumn(List<String> fieldNames, Class businessClass) throws SearchCriteriaException;
}
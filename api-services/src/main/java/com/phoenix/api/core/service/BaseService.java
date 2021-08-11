package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.FilterMetadataEntity;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import com.phoenix.api.core.repository.specification.PredicateBuilder;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public interface BaseService {
    /**
     * @param code Mã lỗi lưu ở bảng fw_exception
     * @return {@link ApplicationException}
     */
    ApplicationException getApplicationException(String code);

    /**
     * @param code Mã lỗi lưu ở bảng fw_exception
     * @return {@link ExceptionEntity}
     */
    ExceptionEntity findExceptionByCode(String code);

    PredicateBuilder getPredicateBuilderFromSearchCriteria(PredicateBuilder predicate, List<SearchCriteria> conditions);

    PredicateBuilder getPredicateBuilderFromSearchCriteria(List<SearchCriteria> conditions, Predicate.BooleanOperator booleanOperator);

    String getPropertyOfRequestBodyByKey(Map requestBody, String key);

    List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests);
}

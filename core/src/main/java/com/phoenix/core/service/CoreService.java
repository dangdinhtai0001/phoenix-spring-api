package com.phoenix.core.service;

import com.phoenix.common.structure.Tuple;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultAuthenticationToken;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.pagination.PaginationOption;
import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.OrderDirection;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchCriteriaRequest;

import java.util.List;
import java.util.Map;

public interface CoreService {
    /**
     * @param code Mã lỗi lưu ở bảng fw_exception
     * @return {@link ApplicationException}
     */
    ApplicationException getApplicationException(String code);

    /**
     * @param code Mã lỗi lưu ở bảng fw_exception
     * @return {@link DefaultException}
     */
    DefaultException findExceptionByCode(String code);

    String getPropertyOfRequestBodyByKey(Map requestBody, String key);

    List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests);

    DefaultAuthenticationToken getCurrentSecurityToken();

    Tuple convertObjectToTuple(Object object, String... fields) throws NoSuchFieldException, IllegalAccessException;

    <T> List<Tuple> convertListObjectToListTuple(List<T> list, String... fields) throws NoSuchFieldException, IllegalAccessException;

    OrderBy getOrderBy(PaginationOption paginationOption, OrderDirection direction);
}

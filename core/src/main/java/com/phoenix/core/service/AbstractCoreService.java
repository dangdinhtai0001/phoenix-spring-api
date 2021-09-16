package com.phoenix.core.service;

import com.phoenix.common.structure.Tuple;
import com.phoenix.common.structure.imp.TriTupleImpl;
import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.DefaultException;
import com.phoenix.core.model.pagination.PaginationOption;
import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.OrderDirection;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchCriteriaRequest;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractCoreService implements CoreService {

    public abstract List<DefaultException> getExceptions();

    @Override
    public ApplicationException getApplicationException(String code) {
        DefaultException exception = findExceptionByCode(code);

        return new ApplicationException(exception.getMessage(), code,
                HttpStatus.valueOf(exception.getHttpCode()));
    }

    @Override
    public DefaultException findExceptionByCode(String code) {
        return getExceptions()
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }

    @Override
    public String getPropertyOfRequestBodyByKey(Map requestBody, String key) {
        Object value = requestBody.get(key);

        if (value == null) {
            return null;
        }

        return String.valueOf(value);
    }

    @Override
    public List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests) {
        if (listConditionRequests == null || listConditionRequests.isEmpty()) {
            return null;
        }
        return listConditionRequests.stream().map(SearchCriteriaRequest::getSearchCriteria).collect(Collectors.toList());
    }

    @Override
    public Tuple convertObjectToTuple(Object object, String... fields) throws NoSuchFieldException, IllegalAccessException {
        Class objectClass = object.getClass();

        Class[] types = new Class[fields.length];
        Object[] args = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            types[i] = ReflectionUtil.getTypeOfFieldByName(objectClass, fields[i]);
            args[i] = ReflectionUtil.getFieldValue(object, fields[i], types[i]);
        }

        return new TriTupleImpl(fields, types, args);
    }

    @Override
    public <T> List<Tuple> convertListObjectToListTuple(List<T> list, String... fields) throws NoSuchFieldException, IllegalAccessException {
        if (list == null || list.isEmpty()) {
            return null;
        }

        List<Tuple> tuples = new LinkedList<>();

        for (Object instance : list) {
            tuples.add(convertObjectToTuple(instance, fields));
        }

        return tuples;
    }

    @Override
    public OrderBy getOrderBy(PaginationOption paginationOption, OrderDirection direction) {
        String[] sortBy = new String[0];
        if (direction == OrderDirection.ASC) {
            sortBy = paginationOption.getSortAscending();
        }
        if (direction == OrderDirection.DESC) {
            sortBy = paginationOption.getSortDescending();
        }

        return new OrderBy(direction, sortBy);


    }
}

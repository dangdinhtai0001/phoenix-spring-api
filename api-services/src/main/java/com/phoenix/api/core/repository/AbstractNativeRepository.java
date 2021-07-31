package com.phoenix.api.core.repository;

import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractNativeRepository implements NativeRepository {
    private final EntityManager entityManager;

    public AbstractNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List executeNativeQuery(String sql, Object... params) {
        Query query = createNativeQuery(sql, params);
        return query.getResultList();
    }

    @Override
    public int updateNativeQuery(String sql, String... params) {
        Query query = createNativeQuery(sql, params);
        return query.executeUpdate();
    }

    @Override
    public Object parseResult(Object[] record, List<Pair<String, Class>> params, Class aClass) throws NoSuchFieldException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Object target = aClass.newInstance();
        Constructor constructor = aClass.getConstructor();
        Object target = constructor.newInstance();
        Pair<String, Class> pair;
        for (int i = 0; i < params.size(); i++) {
            pair = params.get(i);
            ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
        }
        return target;
    }

    @Override
    public List parseResult(List<Object[]> results, List<Pair<String, Class>> params, Class aClass) throws NoSuchFieldException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object target;
        Pair<String, Class> pair;
        List<Object> list = new LinkedList<>();
        Constructor constructor = aClass.getConstructor();

        for (Object[] record : results) {
            target = constructor.newInstance();
            for (int i = 0; i < params.size(); i++) {
                pair = params.get(i);
                ReflectionUtil.setField(pair.first(), pair.second(), String.valueOf(record[i]), target);
            }
            list.add(target);
        }
        return list;
    }

    /**
     * @param conditions
     * @return
     * @throws SearchCriteriaException
     */
    @Override
    public String getConditionClauseFromSearchCriteria(List<SearchCriteria> conditions) throws SearchCriteriaException {
        if (conditions == null || conditions.isEmpty()) {
            return " 1 = 1";
        }

        StringBuilder clause = new StringBuilder();

        for (SearchCriteria criteria : conditions) {
            if (clause.length() > 0) {
                clause.append(" AND ");
            }
            clause.append(criteria.getKey());
            clause.append(" ");

            switch (criteria.getSearchOperation()) {

                case BETWEEN:
//                    handleSearchCriteriaWithTwoArguments(clause, criteria);
                    handleSearchCriteria(clause, criteria, 2);
                    break;
                //--------------------------------------
                case EQUAL:
                case GREATER_THAN_OR_EQUAL:
                case GREATER_THAN:
                case LESS_THAN_OR_EQUAL:
                case LIKE:
                case LESS_THAN:
                case NOT_EQUAL:
                case NOT_LIKE:
//                    handleSearchCriteriaWithOneArgument(clause, criteria);
                    handleSearchCriteria(clause, criteria, 1);
                    break;
                //--------------------------------------
                case IN:
                case NOT_IN:
//                    handleSearchCriteriaWithListArguments(clause, criteria);
                    handleSearchCriteria(clause, criteria, 3);
                    break;
                default:
                    break;
            }
        }

        if (clause.length() > 0) {
            clause.insert(0, "( ");
            clause.append(" )");
        }

        return clause.toString();
    }

    @Override
    public List<Object> getParameterFromSearchCriteria(List<SearchCriteria> conditions) {
        List<Object> list = new LinkedList<>();

        if (conditions == null) {
            return list;
        }

        for (SearchCriteria criteria : conditions) {
            list.addAll(criteria.getArguments());
        }
        return list;
    }

    //    ================================================================
    //
    //    ================================================================

    private Query createNativeQuery(String sql, Object... params) {
        Query query = entityManager.createNativeQuery(sql);

        int index = 1;
        for (Object param : params) {
            query.setParameter(index++, param);
        }

        return query;
    }

    public BasePagination executeNativeQuery(Class aClass, PageRequest pageRequest, String totalSql, String sql, Object... params) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Query query;
        if(params.length == 0){
            query = createNativeQuery(sql);
        }else {
            query = createNativeQuery(sql, params);
        }

        int pageNumber = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();

        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);

        List resultList;
        try {
            resultList = query.getResultList();
        } catch (NullPointerException e) {
            resultList = new LinkedList();
        }

        //parse result
        List<Pair<String, Class>> parseParams = ReflectionUtil.getFieldAsPairList(aClass,
                "id", "name", "dateOfBirth", "gender", "phoneNumber", "avatar", "username");
        List list = parseResult(resultList, parseParams, aClass);

        // find total
        Query queryTotal = createNativeQuery(totalSql, params);
        String countResult;
        try {
            countResult =  String.valueOf(queryTotal.getSingleResult());
        } catch (NullPointerException e) {
            countResult = "0";
        }
        long total = Long.parseLong(countResult);

        Page page = new PageImpl(list, pageRequest, total);

        return new BasePagination(page);
    }

    private void handleSearchCriteria(StringBuilder clause, SearchCriteria criteria, int type) throws SearchCriteriaException {
        clause.append(criteria.getSearchOperation().getSign());
        clause.append(" ");

        if (type == 1) {
            if (criteria.getArguments().size() != 1) {
                throw new SearchCriteriaException("Configuration error of expression");
            }
            clause.append(" ? ");
        }

        if (type == 2) {
            if (criteria.getArguments().size() != 2) {
                throw new SearchCriteriaException("Configuration error of expression");
            }
            clause.append(" ? AND ?");
        }

        if (type == 3) {
            if (criteria.getArguments().isEmpty()) {
                throw new SearchCriteriaException("Configuration error of expression");
            }

            clause.append(" (");

            int size = criteria.getArguments().size();
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    clause.append(" ? ");
                } else {
                    clause.append(" ? ,");
                }
            }

            clause.append(")");
        }
    }
}

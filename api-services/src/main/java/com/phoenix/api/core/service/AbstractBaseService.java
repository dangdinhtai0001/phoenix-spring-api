package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import com.phoenix.api.core.repository.specification.PredicateBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractBaseService implements BaseService {
    private final List<ExceptionEntity> exceptionEntities;

    protected AbstractBaseService(List<ExceptionEntity> exceptionEntities) {
        this.exceptionEntities = exceptionEntities;
    }

    /**
     * @param code Code of exception. Stored in fw_exception table and loaded in context.
     * @return ApplicationException instance
     */
    public ApplicationException getApplicationException(String code) {
        ExceptionEntity exceptionEntity = findExceptionByCode(code);

        return new ApplicationException(exceptionEntity.getMessage(), code, this.getClass().getName(),
                HttpStatus.valueOf(exceptionEntity.getHttpCode()));
    }

    public ExceptionEntity findExceptionByCode(String code) {
        return exceptionEntities
                .stream()
                .filter(exceptionEntity -> code.equals(exceptionEntity.getCode()))
                .findFirst().orElse(null);
    }

    @Override
    public PredicateBuilder getPredicateBuilderFromSearchCriteria(PredicateBuilder predicate, List<SearchCriteria> conditions) {
        for (SearchCriteria criteria : conditions) {
            switch (criteria.getSearchOperation()) {
                case BETWEEN:
                    predicate.between(criteria.getKey(), criteria.getArguments().get(0), criteria.getArguments().get(1));
                    break;
                case EQUAL:
                    predicate.eq(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    predicate.ge(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case GREATER_THAN:
                    predicate.gt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case IN:
                    predicate.in(criteria.getKey(), criteria.getArguments());
                    break;
                case LESS_THAN_OR_EQUAL:
                    predicate.le(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case LIKE:
                    predicate.like(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
                case LESS_THAN:
                    predicate.lt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
                    break;
                case NOT_EQUAL:
                    predicate.ne(criteria.getKey(), criteria.getArguments().get(0));
                    break;
                case NOT_IN:
                    predicate.notIn(criteria.getKey(), criteria.getArguments());
                    break;
                case NOT_LIKE:
                    predicate.notLike(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
                    break;
            }
        }
        return predicate;
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
    public String getConditionClauseFromSearchCriteria(List<SearchCriteria> conditions) throws SearchCriteriaException {
        if (conditions == null || conditions.isEmpty()) {
            return "";
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
    public List<SearchCriteria> getListOfSearchCriteria(List<SearchCriteriaRequest> listConditionRequests) {
        if (listConditionRequests == null || listConditionRequests.isEmpty()) {
            return null;
        }
        return listConditionRequests.stream().map(SearchCriteriaRequest::getSearchCriteria).collect(Collectors.toList());
    }

    /**
     * @param clause   StringBuilder build where clause
     * @param criteria SearchCriteria need to handle
     */
    @Deprecated
    private void handleSearchCriteriaWithOneArgument(StringBuilder clause, SearchCriteria criteria) {
        clause.append(criteria.getSearchOperation().getSign());
        clause.append(" ");

        if (criteria.getArguments().get(0) instanceof String) {
            clause.append("'");
            clause.append(criteria.getArguments().get(0));
            clause.append("'");
        } else {
            clause.append(criteria.getArguments().get(0));
        }
    }

    /**
     * @param clause   StringBuilder build where clause
     * @param criteria SearchCriteria need to handle
     */
    @Deprecated
    private void handleSearchCriteriaWithTwoArguments(StringBuilder clause, SearchCriteria criteria) {
        clause.append(criteria.getSearchOperation().getSign());
        clause.append(" ");

        if (criteria.getArguments().get(0) instanceof String) {
            clause.append("'");
            clause.append(criteria.getArguments().get(0));
            clause.append("'");
        } else {
            clause.append(criteria.getArguments().get(0));
        }

        clause.append(" AND ");

        if (criteria.getArguments().get(1) instanceof String) {
            clause.append("'");
            clause.append(criteria.getArguments().get(1));
            clause.append("'");
        } else {
            clause.append(criteria.getArguments().get(1));
        }
    }

    /**
     * @param clause   StringBuilder build where clause
     * @param criteria SearchCriteria need to handle
     */
    @Deprecated
    private void handleSearchCriteriaWithListArguments(StringBuilder clause, SearchCriteria criteria) {
        clause.append(criteria.getSearchOperation().getSign());
        clause.append(" (");

        String prefix = "";
        for (Object arg : criteria.getArguments()) {
            clause.append(prefix);
            prefix = ",";
            if (arg instanceof String) {
                clause.append("'");
                clause.append(arg);
                clause.append("'");
            } else {
                clause.append(arg);
            }
        }

        clause.append(")");
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

package com.phoenix.api.core.service;

import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class TestBaseService {

    @Test
    public void testGetSqlClauseFromSearchCriteria() throws Exception {
        List<SearchCriteria> conditions = new LinkedList<>();

        conditions.add(new SearchCriteria("id", SearchOperation.BETWEEN, 1, 7));
        conditions.add(new SearchCriteria("id", SearchOperation.GREATER_THAN_OR_EQUAL, 2));
        conditions.add(new SearchCriteria("id", SearchOperation.IN, 5, 6, "7"));
        StringBuilder clause = new StringBuilder();

        for (SearchCriteria criteria : conditions) {
            if (clause.length() > 0) {
                clause.append(" AND ");
            }
            clause.append(criteria.getKey());
            clause.append(" ");

            switch (criteria.getSearchOperation()) {

                case BETWEEN:
                    handleSearchCriteriaWithTwoArguments(clause, criteria);
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
                    handleSearchCriteriaWithOneArgument(clause, criteria);
                    break;
                //--------------------------------------
                case IN:
                case NOT_IN:
                    handleSearchCriteriaWithListArguments(clause, criteria);
                    break;
                default:
                    break;
            }
        }

        if (clause.length() > 0) {
            clause.insert(0, "( ");
            clause.append(" )");
        }


        System.out.println(clause);
    }


    /**
     * @param clause   StringBuilder build where clause
     * @param criteria SearchCriteria need to handle
     */
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
}

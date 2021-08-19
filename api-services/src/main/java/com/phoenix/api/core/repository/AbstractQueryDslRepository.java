package com.phoenix.api.core.repository;

import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractQueryDslRepository {

    private final SQLQueryFactory queryFactory;

    protected AbstractQueryDslRepository(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject) {
        return queryFactory
                .select(expressions)
                .from(relationalPathBaseObject);
    }

    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder) {
        return queryFactory
                .select(expressions)
                .from(pathBuilder);
    }

    public String getTableName(RelationalPathBase relationalPathBase) {
        return relationalPathBase.getTableName();
    }

    public PathBuilder getPathBuilder(String className, String tableName) throws ClassNotFoundException {
        Class<?> entityType = Class.forName(className);
        return new PathBuilder(entityType, tableName);
    }

    public PathBuilder getPathBuilder(Class aClass, String tableName) {
        return new PathBuilder(aClass, tableName);
    }

    public Expression[] getExpressions(PathBuilder pathBuilder, String... properties) {
        Expression[] expressions = new Expression[properties.length];

        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = pathBuilder.get(properties[i]);
        }

        return expressions;
    }

    public QueryBase addWhereClause(SQLQuery query, Predicate predicate) {
        return query.where(predicate);
    }

    public QueryBase addWhereClause(SQLQuery query, List<Predicate> predicates) {
        for (Predicate predicate : predicates) {
            query.where(predicate);
        }
        return query;
    }

    public Predicate getPredicateFromSearchCriteria(PathBuilder pathBuilder, SearchCriteria criteria) {
        String key = criteria.getKey();
        List<String> stringArguments = criteria.getArguments().stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        switch (criteria.getSearchOperation()) {

            case BETWEEN:
                return pathBuilder.getString(key).between(
                        String.valueOf(stringArguments.get(0)),
                        String.valueOf(stringArguments.get(1))
                );
            //--------------------------------------
            case EQUAL:
                return pathBuilder.getString(key).eq(stringArguments.get(0));
            case GREATER_THAN_OR_EQUAL:
                return pathBuilder.getString(key).goe(stringArguments.get(0));
            case GREATER_THAN:
                return pathBuilder.getString(key).gt(stringArguments.get(0));
            case LESS_THAN_OR_EQUAL:
                return pathBuilder.getString(key).loe(stringArguments.get(0));
            case LIKE:
                return pathBuilder.getString(key).like(stringArguments.get(0));
            case LESS_THAN:
                return pathBuilder.getString(key).lt(stringArguments.get(0));
            case NOT_EQUAL:
                return pathBuilder.getString(key).ne(stringArguments.get(0));
            case NOT_LIKE:
                return pathBuilder.getString(key).notLike(stringArguments.get(0));
            //--------------------------------------
            case IN:
                return pathBuilder.getString(key).in(stringArguments);
            case NOT_IN:
                return pathBuilder.getString(key).notIn(stringArguments);
            default:
                return null;
        }
    }

    public List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {
            predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));
        }

        return predicates;
    }

    public SQLQuery join(JoinType joinType, SQLQuery query, PathBuilder sourceBuilder, PathBuilder joinBuilder,
                         String sourceProperty, String joinProperty) {

        Predicate predicate = sourceBuilder.getString(sourceProperty).eq(joinBuilder.getString(joinProperty));

        if (joinType == JoinType.LEFT) {
            query.leftJoin(joinBuilder).on(predicate);
        }

        if (joinType == JoinType.RIGHT) {
            query.rightJoin(joinBuilder).on(predicate);
        }

        if (joinType == JoinType.INNER) {
            query.innerJoin(joinBuilder).on(predicate);
        }

        return query;
    }

    public Expression[] mergeExpressions(Expression[]... expressions) {
        List<Expression> result = new LinkedList<>();

        for (Expression[] expression : expressions) {
            result.addAll(Arrays.asList(expression));
        }

        return result.toArray(new Expression[0]);
    }

}

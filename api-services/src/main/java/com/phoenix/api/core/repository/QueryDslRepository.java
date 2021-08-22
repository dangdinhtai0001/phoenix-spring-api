package com.phoenix.api.core.repository;

import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.OrderDirection;
import com.phoenix.api.core.model.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;

import java.util.List;

public interface QueryDslRepository {

    //---------------------------------

    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject);

    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder);

    //---------------------------------

    String getTableName(RelationalPathBase relationalPathBase);

    PathBuilder getPathBuilder(String className, String tableName) throws ClassNotFoundException;

    PathBuilder getPathBuilder(Class aClass, String tableName);

    //---------------------------------

    PathBuilder getPathBuilder(Class aClass, RelationalPathBase relationalPathBase);

    PathBuilder getPathBuilder(List<PathBuilder> pathBuilders, String anotationTableName);

    Expression[] getExpressions(PathBuilder pathBuilder, String... properties);

    Expression[] mergeExpressions(Expression[]... expressions);

    //---------------------------------

    QueryBase addWhereClause(SQLQuery query, Predicate predicate);

    QueryBase addWhereClause(SQLQuery query, List<Predicate> predicates);

    //---------------------------------

    Predicate getPredicateFromSearchCriteria(PathBuilder pathBuilder, SearchCriteria criteria);

    List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList);

    //---------------------------------

    List<Predicate> getPredicateFromSearchCriteria(List<PathBuilder> pathBuilders, List<SearchCriteria> searchCriteriaList);

    List<Object> parseResult(List<Tuple> queryResult, Class<?> aClass, String... properties);

    //---------------------------------

    SQLQuery join(JoinType joinType, SQLQuery query, PathBuilder sourceBuilder, PathBuilder joinBuilder,
                  String sourceProperty, String joinProperty);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, String property, OrderDirection direction);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, OrderBy orderBy);

    SQLQuery addOrderBy(SQLQuery query, List<PathBuilder> pathBuilders, OrderBy orderBy);
}

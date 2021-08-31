package com.phoenix.api.core.repository;

import com.phoenix.api.core.model.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BaseQueryDslRepository {

    //---------------------------------

    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject);

    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder);

    //---------------------------------

    String getTableName(RelationalPathBase relationalPathBase);

    String getTableName(String key, Class objectClass);

    String getDefaultSchemaName();

    PathBuilder getPathBuilder(String className, String tableName) throws ClassNotFoundException;

    RelationalPath getRelationalPathBase(Class<? extends RelationalPathBase> typeClass, RelationalPath relationalPath);

    PathBuilder getPathBuilder(Class<? extends RelationalPathBase> aClass, String tableName);

    PathBuilder getPathBuilder(List<PathBuilder> pathBuilders, String anotationTableName);

    StringPath getPathString(PathBuilder pathBuilder, String property);

    StringPath getPathString(Class<? extends RelationalPathBase> aClass, String tableName, String property);

    StringPath getPathString(Class<? extends RelationalPathBase> aClass, RelationalPathBase relationalPathBase, String property);

    Expression[] getExpressions(PathBuilder pathBuilder, String... properties);

    //---------------------------------

    PathBuilder getPathBuilder(Class<? extends RelationalPathBase> aClass, RelationalPathBase relationalPathBase);

    Expression[] mergeExpressions(Expression[]... expressions);

    //---------------------------------

    QueryBase addWhereClause (List<QueryExpression> expressions, SQLQuery query);

    QueryBase addWhereClause(SQLQuery query, QueryExpression expression);

    QueryBase addWhereClause(SQLQuery query, Predicate predicate);

    QueryBase addWhereClause(SQLQuery query, PathBuilder pathBuilder, SearchCriteria criteria);

    QueryBase addWhereClause(SQLQuery query, List<Predicate> predicates);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, Predicate predicate);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, PathBuilder pathBuilder, SearchCriteria criteria);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, List<Predicate> predicates);

    //---------------------------------

    Predicate getPredicateFromSearchCriteria(PathBuilder pathBuilder, SearchCriteria criteria);

    List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList);

    List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder> pathBuilders, List<SearchCriteria> searchCriteriaList);

    List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder> pathBuilders, SearchCriteria criteria);

    //---------------------------------

    List<Object> parseResult(List<Tuple> queryResult, Class<?> aClass, String... properties);

    BasePagination fetchWithPagination(PageRequest pageRequest, SQLQuery query, Class aClass, String... properties);

    //---------------------------------

    SQLQuery join(JoinType joinType, SQLQuery query, PathBuilder sourceBuilder, PathBuilder joinBuilder,
                  String sourceProperty, String joinProperty);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, String property, OrderDirection direction);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, List<String> properties, OrderDirection direction);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, OrderBy orderBy);

    SQLQuery addOrderBy(SQLQuery query, Class objectClass, List<PathBuilder> pathBuilders, OrderBy orderBy);

    SQLUpdateClause set(SQLUpdateClause query, Path path, Object value);
}

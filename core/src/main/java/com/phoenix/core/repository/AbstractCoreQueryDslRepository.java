package com.phoenix.core.repository;

import com.phoenix.common.util.ReflectionUtil;
import com.phoenix.core.annotation.BusinessObjectField;
import com.phoenix.core.model.query.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.springframework.beans.factory.annotation.Value;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractCoreQueryDslRepository implements CoreQueryDslRepository {

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    private final SQLQueryFactory queryFactory;

    protected AbstractCoreQueryDslRepository(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    //---------------------------------------------------------------------------------------------------

    protected abstract com.phoenix.common.structure.Tuple getRelationalPathMap();

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> SQLQuery<Tuple> createSelectQuery(RelationalPathBase<T> relationalPathBase,
                                                                               Path<?>... paths) {
        return queryFactory
                .select(paths)
                .from(relationalPathBase);
    }

    @Override
    public <T extends RelationalPathBase<T>> SQLQuery<Tuple> createSelectQuery(RelationalPathBase<T> relationalPathBase,
                                                                               String... columns) {
        Path<?>[] paths = getPaths(relationalPathBase, columns);
        return createSelectQuery(relationalPathBase, paths);
    }

    @Override
    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject) {
        return queryFactory
                .select(expressions)
                .from(relationalPathBaseObject);
    }

    @Override
    public SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder) {
        return queryFactory
                .select(expressions)
                .from(pathBuilder);
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T> String getTableName(RelationalPathBase<T> relationalPathBase) {
        return relationalPathBase.getTableName();
    }

    @Override
    public String getTableName(String key, Class<?> objectClass) {
        BusinessObjectField annotation = (BusinessObjectField) ReflectionUtil.getAnnotationOfField(key, objectClass, BusinessObjectField.class);
        return annotation.table();
    }

    @Override
    public String getDefaultSchemaName() {
        return this.datasourceUsername;
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(Class<T> aClass, String tableName) {
        return new PathBuilder<>(aClass, tableName);
    }

    @Override
    public <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(Class<T> aClass, RelationalPathBase<T> relationalPathBase) {
        String tableName = getTableName(relationalPathBase);
        return new PathBuilder<>(aClass, tableName);
    }

    @Override
    public PathBuilder<?> getPathBuilder(List<PathBuilder<?>> pathBuilders, String tableName) {
        for (PathBuilder<?> pathBuilder : pathBuilders) {
            if (tableName.equals(pathBuilder.getMetadata().getName())) {
                return pathBuilder;
            }
        }
        return null;
    }

    @Override
    public <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(RelationalPathBase<T> relationalPathBase) {
        Class<? extends T> relationalPathBaseType = relationalPathBase.getType();
        String tableName = relationalPathBase.getTableName();
        return new PathBuilder<>(relationalPathBaseType, tableName);
    }

    @Override
    public <T extends RelationalPathBase<T>> RelationalPathBase<T> getRelationalPathBase(Class<T> typeClass, RelationalPath<T> relationalPath) {
        String table = relationalPath.getTableName();
        String schema = getDefaultSchemaName();
        return new RelationalPathBase<>(typeClass, table, schema, table);
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> StringPath getPathString(PathBuilder<T> pathBuilder, String column) {
        return pathBuilder.getString(column);
    }

    @Override
    public <T extends RelationalPathBase<T>> StringPath getPathString(Class<T> RelationalPathBaseClass, String tableName, String column) {
        PathBuilder<T> pathBuilder = getPathBuilder(RelationalPathBaseClass, tableName);
        return pathBuilder.getString(column);
    }

    @Override
    public <T extends RelationalPathBase<T>> StringPath getPathString(Class<T> RelationalPathBaseClass, RelationalPathBase<T> relationalPathBase, String column) {
        PathBuilder<T> pathBuilder = getPathBuilder(RelationalPathBaseClass, relationalPathBase);
        return pathBuilder.getString(column);
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> Path<T>[] getPaths(RelationalPathBase<T> relationalPathBase, String... columns) {
        List<Path<?>> pathList = relationalPathBase.getColumns();
        List<Path<?>> result = new ArrayList<>(columns.length);

        for (String column : columns) {
            for (Path<?> path : pathList) {
                if (path.getMetadata().getName().equals(column)) {
                    result.add(path);
                    break;
                }
            }
        }

        return result.toArray(new Path[0]);
    }

    @Override
    public Path<?>[] mergePath(Path<?>[]... paths) {
        List<Path<?>> result = new LinkedList<>();

        for (Path<?>[] path : paths) {
            result.addAll(Arrays.asList(path));
        }

        return result.toArray(new Path<?>[0]);
    }

    @Override
    public Expression[] getExpressions(PathBuilder pathBuilder, String... properties) {
        Expression[] expressions = new Expression[properties.length];

        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = pathBuilder.get(properties[i]);
        }

        return expressions;
    }

    @Override
    public Expression[] mergeExpressions(Expression[]... expressions) {
        List<Expression> result = new LinkedList<>();

        for (Expression[] expression : expressions) {
            result.addAll(Arrays.asList(expression));
        }

        return result.toArray(new Expression[0]);
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public QueryBase<?> addWhereClause(SQLQuery<?> query, QueryExpression expression) {
        if (expression.getType() == ExpressionType.BOOLEAN) {
            query.where(Expressions.booleanTemplate(expression.getExpression(), expression.getArguments()));
        }
        return query;
    }

    @Override
    public QueryBase<?> addWhereClause(List<QueryExpression> expressions, SQLQuery<?> query) {
        if (expressions == null || expressions.size() == 0) {
            return query;
        }

        for (QueryExpression expression : expressions) {
            addWhereClause(query, expression);
        }

        return query;
    }

    @Override
    public QueryBase<?> addWhereClause(SQLQuery<?> query, Predicate predicate) {
        return query.where(predicate);
    }

    @Override
    public QueryBase<?> addWhereClause(SQLQuery<?> query, PathBuilder pathBuilder, SearchCriteria criteria) {
        Predicate predicate = getPredicateFromSearchCriteria(pathBuilder, criteria);
        return query.where(predicate);
    }

    @Override
    public <T extends RelationalPathBase<T>> QueryBase<?> addWhereClause(SQLQuery<?> query, RelationalPathBase<T> relationalPathBase, SearchCriteria criteria) {
        PathBuilder<T> pathBuilder = getPathBuilder(relationalPathBase);

        Predicate predicate = getPredicateFromSearchCriteria(pathBuilder, criteria);
        return query.where(predicate);
    }

    @Override
    public QueryBase<?> addWhereClause(SQLQuery<?> query, List<Predicate> predicates) {
        for (Predicate predicate : predicates) {
            query.where(predicate);
        }
        return query;
    }

    @Override
    public SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, Predicate predicate) {
        return sqlUpdateClause.where(predicate);
    }

    @Override
    public SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, PathBuilder pathBuilder, SearchCriteria criteria) {
        Predicate predicate = getPredicateFromSearchCriteria(pathBuilder, criteria);
        return sqlUpdateClause.where(predicate);
    }

    @Override
    public SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, List<Predicate> predicates) {
        for (Predicate predicate : predicates) {
            sqlUpdateClause.where(predicate);
        }
        return sqlUpdateClause;
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> Predicate getPredicateFromSearchCriteria(RelationalPathBase<T> relationalPathBase, SearchCriteria criteria) {
        PathBuilder<T> pathBuilder = getPathBuilder(relationalPathBase);
        return getPredicateFromSearchCriteria(pathBuilder, criteria);
    }

    @Override
    public <T extends RelationalPathBase<T>> List<Predicate> getPredicateFromSearchCriteria(RelationalPathBase<T> relationalPathBase, List<SearchCriteria> searchCriteriaList) {

        PathBuilder<T> pathBuilder = getPathBuilder(relationalPathBase);

        return getPredicateFromSearchCriteria(pathBuilder, searchCriteriaList);
    }

    @Override
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
            case IN:
                return pathBuilder.getString(key).in(stringArguments);
            case NOT_IN:
                return pathBuilder.getString(key).notIn(stringArguments);
            default:
                return null;
        }
    }

    @Override
    public List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return predicates;
        }

        for (SearchCriteria criteria : searchCriteriaList) {
            predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));
        }

        return predicates;
    }

    @Override
    public List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder<?>> pathBuilders, List<SearchCriteria> searchCriteriaList) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteriaList == null || searchCriteriaList.isEmpty()) {
            return predicates;
        }

        String key;
        String annotationTableName;
        PathBuilder pathBuilder;

        for (SearchCriteria criteria : searchCriteriaList) {
            key = criteria.getKey();
            annotationTableName = getTableName(key, objectClass);
            pathBuilder = getPathBuilder(pathBuilders, annotationTableName);

            predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));
        }

        return predicates;
    }

    @Override
    public List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder<?>> pathBuilders, SearchCriteria criteria) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria == null) {
            return predicates;
        }

        String key;
        String annotationTableName;
        PathBuilder pathBuilder;


        key = criteria.getKey();
        annotationTableName = getTableName(key, objectClass);
        pathBuilder = getPathBuilder(pathBuilders, annotationTableName);

        predicates.add(getPredicateFromSearchCriteria(pathBuilder, criteria));


        return predicates;
    }
    //---------------------------------------------------------------------------------------------------

    @Override
    public <T> List<T> parseResult(List<Tuple> queryResult, Class<T> instanceClass, String... properties) {
        List<T> result = new LinkedList<>();

        for (Tuple record : queryResult) {
            T instance = null;

            try {
                instance = ReflectionUtil.createInstance(instanceClass);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < properties.length; i++) {
                try {
                    ReflectionUtil.setField(instance, properties[i],
                            record.get(i, ReflectionUtil.getTypeOfFieldByName(instanceClass, properties[i])));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            result.add(instance);
        }

        return result;
    }

    //---------------------------------------------------------------------------------------------------
    @Override
    public <T extends RelationalPathBase<T>> SQLQuery join(JoinType joinType, SQLQuery query,
                                                           RelationalPathBase<T> leftRelationalPathBase, RelationalPathBase<T> rightRelationalPathBase,
                                                           String leftColumn, String rightColumn) {

        PathBuilder<T> leftBuilder = getPathBuilder(leftRelationalPathBase);
        PathBuilder<T> rightBuilder = getPathBuilder(rightRelationalPathBase);

        return join(joinType, query, leftBuilder, rightBuilder, leftColumn, rightColumn);
    }

    @Override
    public <T extends RelationalPathBase<T>, E extends RelationalPathBase<E>> SQLQuery join(
            JoinType joinType, SQLQuery query, PathBuilder<T> leftBuilder, PathBuilder<E> rightBuilder,
            String leftColumn, String rightColumn) {

        Predicate predicate = leftBuilder.getString(leftColumn).eq(rightBuilder.getString(rightColumn));

        if (joinType == JoinType.LEFT_JOIN) {
            query.leftJoin(rightBuilder).on(predicate);
        }

        if (joinType == JoinType.RIGHT_JOIN) {
            query.rightJoin(rightBuilder).on(predicate);
        }

        if (joinType == JoinType.INNER_JOIN) {
            query.innerJoin(rightBuilder).on(predicate);
        }

        if (joinType == JoinType.JOIN) {
            query.join(rightBuilder).on(predicate);
        }

        if (joinType == JoinType.FULL_JOIN) {
            query.fullJoin(rightBuilder).on(predicate);
        }

        return query;
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public <T extends RelationalPathBase<T>> SQLQuery addOrderBy(SQLQuery query, RelationalPathBase<T> relationalPathBase, OrderBy orderBy) {
        List<String> keys = orderBy.getKeys();
        PathBuilder<T> pathBuilder = getPathBuilder(relationalPathBase);

        query = addOrderBy(query, pathBuilder, keys, orderBy.getDirection());

        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, String property, OrderDirection direction) {
        if (direction == OrderDirection.DESC) {
            query.orderBy(pathBuilder.getString(property).desc());
        }

        if (direction == OrderDirection.ASC) {
            query.orderBy(pathBuilder.getString(property).asc());
        }

        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, List<String> properties, OrderDirection direction) {
        OrderSpecifier[] orderSpecifiers = new OrderSpecifier[0];
        if (direction == OrderDirection.DESC) {
            orderSpecifiers = properties.stream()
                    .map(property -> pathBuilder.getString(property).desc())
                    .toArray(OrderSpecifier[]::new);
        }

        if (direction == OrderDirection.ASC) {
            orderSpecifiers = properties.stream()
                    .map(property -> pathBuilder.getString(property).asc())
                    .toArray(OrderSpecifier[]::new);
        }

        query.orderBy(orderSpecifiers);

        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, OrderBy orderBy) {
        List<String> keys = orderBy.getKeys();

        query = addOrderBy(query, pathBuilder, keys, orderBy.getDirection());

        return query;
    }

    @Override
    public SQLQuery addOrderBy(SQLQuery query, Class objectClass, List<PathBuilder<?>> pathBuilders, OrderBy orderBy) {
        List<String> keys = orderBy.getKeys();

        String annotationTableName;
        PathBuilder pathBuilder;
        for (String key : keys) {
            annotationTableName = getTableName(key, objectClass);
            pathBuilder = getPathBuilder(pathBuilders, annotationTableName);
            query = addOrderBy(query, pathBuilder, key, orderBy.getDirection());
        }

        return query;
    }

    //---------------------------------------------------------------------------------------------------

    @Override
    public SQLUpdateClause set(SQLUpdateClause query, Path path, Object value) {
        query.set(path, value);

        return query;
    }

    //---------------------------------------------------------------------------------------------------
    @Override
    public <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase, Path[] paths, Object[] values) {
        SQLInsertClause insert = queryFactory.insert(relationalPathBase);

        if (paths == null || paths.length < 1) {
            insert.values(values);
        } else {
            insert.columns(paths).values(values);
        }

        return insert;
    }

    @Override
    public <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                                List<com.phoenix.common.structure.Tuple> tuples) {
        SQLInsertClause sqlInsertClause = queryFactory.insert(relationalPathBase);

        if (tuples == null || tuples.isEmpty()) {
            return null;
        }

        Path[] columns = getPaths(relationalPathBase, tuples.get(0).getArrayExpressions());

        for (com.phoenix.common.structure.Tuple tuple : tuples) {
            sqlInsertClause.columns(columns).values(tuple.toArray()).addBatch();
        }

        return sqlInsertClause;
    }

    @Override
    public <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                                com.phoenix.common.structure.Tuple tuple) {
        SQLInsertClause sqlInsertClause = queryFactory.insert(relationalPathBase);

        if (tuple == null) {
            return null;
        }

        Path<?>[] columns = getPaths(relationalPathBase, tuple.getArrayExpressions());

        sqlInsertClause.columns(columns).values(tuple.toArray()).addBatch();

        return sqlInsertClause;
    }

    @Override
    public <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                                SubQueryExpression subQueryExpression, String... columns) {
        Path<?>[] columnPaths = getPaths(relationalPathBase, columns);

        SQLInsertClause insert = queryFactory.insert(relationalPathBase);

        return insert.columns(columnPaths)
                .select(subQueryExpression);
    }

    //---------------------------------------------------------------------------------------------------


}

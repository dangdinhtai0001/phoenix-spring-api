package com.phoenix.core.repository;

import com.phoenix.core.model.query.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;

import java.util.List;

public interface CoreQueryDslRepository {

    //---------------------------------------------------------------------------------------------------

    /**
     * @param relationalPathBase (something like : QFwResourceAction.fwResourceAction)
     * @param paths              {@link com.querydsl.core.types.Path}
     * @param <T>                Ví dụ: QFwMenu.fwMenu {@link com.querydsl.sql.RelationalPathBase}
     * @return {@link SQLQuery} Đối tượng dùng để thực hiện lệnh select
     */
    <T extends RelationalPathBase<T>> SQLQuery<Tuple> createSelectQuery(RelationalPathBase<T> relationalPathBase,
                                                                        Path<?>... paths);

    /**
     * @param relationalPathBase (something like : QFwResourceAction.fwResourceAction)
     * @param columns            Danh sách tên các column cần select
     * @param <T>                Ví dụ: QFwMenu.fwMenu {@link com.querydsl.sql.RelationalPathBase}
     * @return {@link SQLQuery} Đối tượng dùng để thực hiện lệnh select
     */
    <T extends RelationalPathBase<T>> SQLQuery<Tuple> createSelectQuery(RelationalPathBase<T> relationalPathBase,
                                                                        String... columns);

    /**
     * @param expressions              Tương ứng với các cột trong mệnh đề select trong lệnh select (Thường lấy qua {@link #getExpressions(PathBuilder, String...)} )
     * @param relationalPathBaseObject Tương ứng với mệnh đề from trong lệnh select (something like: QFwUser.fwUser)
     * @return Đối tượng SQLQuery dùng để thực thi truy vấn
     */
    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, RelationalPathBase relationalPathBaseObject);

    /**
     * @param expressions Tương ứng với các cột trong mệnh đề select trong lệnh select (Thường lấy qua {@link #getExpressions(PathBuilder, String...)} )
     * @param pathBuilder Tương ứng với mệnh đề from trong lệnh select
     * @return Đối tượng SQLQuery dùng để thực thi truy vấn
     */
    SQLQuery<Tuple> createSelectQuery(Expression[] expressions, PathBuilder pathBuilder);

    //---------------------------------------------------------------------------------------------------

    /**
     * @param relationalPathBase something like: QFwUser.fwUser
     * @return Tên bảng
     */
    <T> String getTableName(RelationalPathBase<T> relationalPathBase);

    /**
     * @param key         Tên field của business class.
     * @param objectClass Business object
     * @return Tên của table map với field của business class
     * @Note: Trường key của business class yêu cầu phải được đánh dấu bằng @BusinessObjectField và đc định nghĩa các tham số
     */
    String getTableName(String key, Class<?> objectClass);

    /**
     * @return Tên của current schema
     * @Note (Đang lấy dựa trên username của datasource được cấu hình trong file properties)
     */
    String getDefaultSchemaName();

    //---------------------------------------------------------------------------------------------------

    /**
     * @param aClass    Class (phải kế thừa từ {@link RelationalPathBase})
     * @param tableName Tên bảng tương ứng
     * @return PathBuilder - Ánh xạ giữa bảng trong database và ứng dụng. Xem thêm tại {@link PathBuilder}
     */
    <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(Class<T> aClass, String tableName);

    /**
     * @param aClass             Class kế thừa từ {@link com.querydsl.sql.RelationalPathBase} (Ví dụ : QFwMenu.class)
     * @param relationalPathBase Ví dụ:  QFwMenu.fwMenu {@link com.querydsl.sql.RelationalPathBase}
     * @param <T>                Class kế thừa từ com.querydsl.sql.RelationalPathBase (Ví dụ : QFwMenu.class)
     * @return PathBuilder - Ánh xạ giữa bảng trong database và ứng dụng. Xem thêm tại {@link PathBuilder}
     */
    <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(Class<T> aClass, RelationalPathBase<T> relationalPathBase);

    /**
     * @param pathBuilders Danh sách các {@link PathBuilder}
     * @param tableName    Tên bảng
     * @return PathBuilder - Ánh xạ giữa bảng trong database và ứng dụng. Xem thêm tại {@link PathBuilder}
     * @Note: Tìm PathBuilder trong list với pathBuilder.getMetadata().getName() == tableName
     */
    PathBuilder<?> getPathBuilder(List<PathBuilder<?>> pathBuilders, String tableName);

    <T extends RelationalPathBase<T>> PathBuilder<T> getPathBuilder(RelationalPathBase<T> relationalPathBase);

    /**
     * @param typeClass      ví dụ: QFwUser.class
     * @param relationalPath ví dụ: QFwUser.fwUser
     * @return RelationalPath {@link com.querydsl.sql.RelationalPath}
     * @Note Hàm này đang gặp lỗi getColumns return null => dùng khai báo bình thường dạng
     * new QFwException("fw_exception", getDefaultSchemaName())
     * để tahy thế
     */
    <T extends RelationalPathBase<T>> RelationalPathBase<T> getRelationalPathBase(Class<T> typeClass, RelationalPath<T> relationalPath);

    //---------------------------------------------------------------------------------------------------

    /**
     * @param pathBuilder {@link com.querydsl.core.types.dsl.PathBuilder}
     * @param column      Tên cột trong database
     * @param <T>         Class kế thừa từ {@link com.querydsl.sql.RelationalPathBase}
     * @return {@link com.querydsl.core.types.dsl.StringPath}
     */
    <T extends RelationalPathBase<T>> StringPath getPathString(PathBuilder<T> pathBuilder, String column);

    /**
     * @param RelationalPathBaseClass Class được querydsl plugin generate ra, ví dụ: QFwMenu.class
     * @param tableName               Tên bảng tương ứng với RelationalPathBaseClass
     * @param column                  Tên cột trong database
     * @param <T>                     Class kế thừa từ {@link com.querydsl.sql.RelationalPathBase}. Ở đây chính là RelationalPathBaseClass
     * @return {@link com.querydsl.core.types.dsl.StringPath}
     */
    <T extends RelationalPathBase<T>> StringPath getPathString(Class<T> RelationalPathBaseClass, String tableName, String column);

    /**
     * @param RelationalPathBaseClass Class được querydsl plugin generate ra, ví dụ: QFwMenu.class
     * @param relationalPathBase      Class kế thừa từ {@link com.querydsl.sql.RelationalPathBase}. Ở đây chính là RelationalPathBaseClass
     * @param column                  Tên cột trong database
     * @param <T>                     Tên cột trong database
     * @return
     */
    <T extends RelationalPathBase<T>> StringPath getPathString(Class<T> RelationalPathBaseClass, RelationalPathBase<T> relationalPathBase, String column);

    //---------------------------------------------------------------------------------------------------

    <T extends RelationalPathBase<T>> Path<T>[] getPaths(RelationalPathBase<T> relationalPathBase, String... columns);

    Path<?>[] mergePath(Path<?>[]... paths);

    Expression[] getExpressions(PathBuilder pathBuilder, String... properties);

    Expression[] mergeExpressions(Expression[]... expressions);

    //---------------------------------------------------------------------------------------------------

    QueryBase<?> addWhereClause(SQLQuery<?> query, QueryExpression expression);

    QueryBase<?> addWhereClause(List<QueryExpression> expressions, SQLQuery<?> query);

    QueryBase<?> addWhereClause(SQLQuery<?> query, Predicate predicate);

    QueryBase<?> addWhereClause(SQLQuery<?> query, PathBuilder pathBuilder, SearchCriteria criteria);

    <T extends RelationalPathBase<T>> QueryBase<?> addWhereClause(SQLQuery<?> query, RelationalPathBase<T> relationalPathBase, SearchCriteria criteria);

    QueryBase<?> addWhereClause(SQLQuery<?> query, List<Predicate> predicates);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, Predicate predicate);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, PathBuilder pathBuilder, SearchCriteria criteria);

    SQLUpdateClause addWhereClause(SQLUpdateClause sqlUpdateClause, List<Predicate> predicates);

    //---------------------------------------------------------------------------------------------------

    <T extends RelationalPathBase<T>> Predicate getPredicateFromSearchCriteria(RelationalPathBase<T> relationalPathBase, SearchCriteria criteria);

    <T extends RelationalPathBase<T>> List<Predicate> getPredicateFromSearchCriteria(RelationalPathBase<T> relationalPathBase, List<SearchCriteria> searchCriteriaList);

    Predicate getPredicateFromSearchCriteria(PathBuilder pathBuilder, SearchCriteria criteria);

    List<Predicate> getPredicateFromSearchCriteria(PathBuilder pathBuilder, List<SearchCriteria> searchCriteriaList);

    List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder<?>> pathBuilders, List<SearchCriteria> searchCriteriaList);

    List<Predicate> getPredicateFromSearchCriteria(Class objectClass, List<PathBuilder<?>> pathBuilders, SearchCriteria criteria);

    //---------------------------------------------------------------------------------------------------

    <T> List<T> parseResult(List<Tuple> queryResult, Class<T> instanceClass, String... properties);

    //---------------------------------------------------------------------------------------------------

    <T extends RelationalPathBase<T>> SQLQuery join(JoinType joinType, SQLQuery query,
                                                    RelationalPathBase<T> leftRelationalPathBase, RelationalPathBase<T> rightRelationalPathBase,
                                                    String leftColumn, String rightColumn);

    /**
     * @param joinType     {@link com.phoenix.core.model.query.JoinType} Kiểu join (DEFAULT, INNER_JOIN, JOIN, LEFT_JOIN, RIGHT_JOIN, FULL_JOIN)
     * @param query        Đối tượng SQLQuery
     * @param leftBuilder  PathBuilder của mệnh đề bên trái lệnh join
     * @param rightBuilder PathBuilder của mệnh đề bên phải lệnh join
     * @param leftColumn   Tên columns ứng vs leftBuilder
     * @param rightColumn  Tên columns ứng vs RightBuilder
     * @param <T>          extends {@link RelationalPathBase}
     * @param <E>          extends {@link RelationalPathBase}
     * @return SQLQuery ban đầu nhưng được thêm mệnh đề join
     */
    <T extends RelationalPathBase<T>, E extends RelationalPathBase<E>> SQLQuery join(
            JoinType joinType, SQLQuery query,
            PathBuilder<T> leftBuilder, PathBuilder<E> rightBuilder,
            String leftColumn, String rightColumn);

    <T extends RelationalPathBase<T>> SQLQuery addOrderBy(SQLQuery query, RelationalPathBase<T> relationalPathBase, OrderBy orderBy);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, String property, OrderDirection direction);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, List<String> properties, OrderDirection direction);

    SQLQuery addOrderBy(SQLQuery query, PathBuilder pathBuilder, OrderBy orderBy);

    SQLQuery addOrderBy(SQLQuery query, Class objectClass, List<PathBuilder<?>> pathBuilders, OrderBy orderBy);

    SQLUpdateClause set(SQLUpdateClause query, Path path, Object value);

    //---------------------------------------------------------------------------------------------------

    <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase, Path[] paths, Object[] values);

    <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                         List<com.phoenix.common.structure.Tuple> tuples);

    <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                         com.phoenix.common.structure.Tuple tuple);

    <T extends RelationalPathBase<T>> SQLInsertClause createInsertClause(RelationalPathBase<T> relationalPathBase,
                                                                         SubQueryExpression subQueryExpression, String... columns);
}

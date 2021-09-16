package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.UserPrincipal;
import com.phoenix.base.model.querydsl.QFwUser;
import com.phoenix.base.model.querydsl.QFwUserGroup;
import com.phoenix.base.model.querydsl.QFwUserGroupMapping;
import com.phoenix.base.model.querydsl.QFwUserStatus;
import com.phoenix.base.repository.UserRepository;
import com.phoenix.common.structure.imp.DiTupleImpl;
import com.phoenix.core.model.query.JoinType;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.model.query.SearchOperation;
import com.phoenix.core.repository.AbstractCoreQueryDslRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository(BeanIds.BASE_USER_REPOSITORY_IMP)
@Log4j2
public class DefaultUserDetailsRepository extends AbstractCoreQueryDslRepository implements UserRepository {

    private final SQLQueryFactory queryFactory;

    protected DefaultUserDetailsRepository(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    @Override
    protected com.phoenix.common.structure.Tuple getRelationalPathMap() {
        RelationalPathBase<QFwUser> userRelationalPath = getRelationalPathBase(QFwUser.class, QFwUser.fwUser);
        RelationalPathBase<QFwUserStatus> userStatusRelationalPath = getRelationalPathBase(QFwUserStatus.class, QFwUserStatus.fwUserStatus);
        RelationalPathBase<QFwUserGroup> userGroupRelationalPath = getRelationalPathBase(QFwUserGroup.class, QFwUserGroup.fwUserGroup);
        RelationalPathBase<QFwUserGroupMapping> userGroupMappingRelationalPath = getRelationalPathBase(QFwUserGroupMapping.class, QFwUserGroupMapping.fwUserGroupMapping);

        String[] expressions = {userRelationalPath.getTableName(), userStatusRelationalPath.getTableName(),
                userGroupRelationalPath.getTableName(), userGroupMappingRelationalPath.getTableName()};
        Object[] args = {userRelationalPath, userStatusRelationalPath, userGroupRelationalPath, userGroupMappingRelationalPath};

        return new DiTupleImpl(expressions, args);
    }

    @Override
    public Optional<UserPrincipal> findUserPrincipalByUsername(String username) {
        RelationalPathBase<QFwUser> userPath = getRelationalPathMap().get(QFwUser.fwUser.getTableName(), RelationalPathBase.class);
        RelationalPathBase<QFwUserStatus> userStatusPath = getRelationalPathMap().get(QFwUserStatus.fwUserStatus.getTableName(), RelationalPathBase.class);

        Path<?>[] userColumns = getPaths(userPath, "id", "username", "password", "hash_algorithm", "password_salt");
        Path<?>[] userStatusColumns = getPaths(userStatusPath, "name");
        Path<?>[] columns = mergePath(userColumns, userStatusColumns);

        SQLQuery query = queryFactory.select(columns).from(userPath);


        join(JoinType.RIGHT_JOIN, query, getPathBuilder(userPath), getPathBuilder(userStatusPath), "status_id", "id");

        SearchCriteria criteria = new SearchCriteria("username", SearchOperation.EQUAL, username);
        addWhereClause(query, userPath, criteria);

        List<Tuple> queryResult = query.fetch();
        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        Tuple record = queryResult.get(0);
        UserPrincipal userPrincipal = new UserPrincipal();

        userPrincipal.setId(record.get(0, Long.class));
        userPrincipal.setUsername(record.get(1, String.class));
        userPrincipal.setPassword(record.get(2, String.class));
        userPrincipal.setHashAlgorithm(record.get(3, String.class));
        userPrincipal.setPasswordSalt(record.get(4, String.class));
        userPrincipal.setStatus(record.get(5, String.class));

        log.debug(query.getSQL().getSQL());

        return Optional.of(userPrincipal);
    }

    @Override
    public List findGroupIdsByUsername(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder userGroupPathBuilder = getPathBuilder(QFwUserGroup.class, QFwUserGroup.fwUserGroup);
        PathBuilder userGroupMappingPathBuilder = getPathBuilder(QFwUserGroupMapping.class, QFwUserGroupMapping.fwUserGroupMapping);

        Expression[] expressions = getExpressions(userGroupPathBuilder, "id");

        SQLQuery query = queryFactory.select(expressions).from(userPathBuilder);

        join(JoinType.LEFT_JOIN, query, userPathBuilder, userGroupMappingPathBuilder, "id", "user_id");
        join(JoinType.LEFT_JOIN, query, userGroupMappingPathBuilder, userGroupPathBuilder, "group_id", "id");

        SearchCriteria criteria = new SearchCriteria("username", SearchOperation.EQUAL, username);
        addWhereClause(query, userPathBuilder, criteria);

        log.debug(query.getSQL().getSQL());

        List<Tuple> queryResult = query.fetch();

        return queryResult.stream().map(tuple -> tuple.get(0, Integer.class)).collect(Collectors.toList());
    }

    @Override
    public int updateRefreshTokenByUsername(String refreshToken, String username) {
        RelationalPathBase<QFwUser> relationalPath = getRelationalPathBase(QFwUser.class, QFwUser.fwUser);

        SQLUpdateClause query = queryFactory.update(relationalPath);

        addWhereClause(query, getEqualsUsernamePredicate(username));

        StringPath pathBuilder = getPathString(QFwUser.class, QFwUser.fwUser, "refresh_token");

        set(query, pathBuilder, refreshToken);

        return (int) query.execute();
    }

    @Override
    public Optional findRefreshTokenByUsername(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);

        Expression[] expressions = getExpressions(userPathBuilder, "refresh_token");

        SQLQuery query = queryFactory.select(expressions).from(userPathBuilder);

        SearchCriteria criteria = new SearchCriteria("username", SearchOperation.EQUAL, username);
        addWhereClause(query, userPathBuilder, criteria);

        log.debug(query.getSQL().getSQL());

        Tuple queryResult = (Tuple) query.fetchOne();

        return Optional.ofNullable(queryResult.get(0, String.class));
    }

    //===========================================

    private Predicate getEqualsUsernamePredicate(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        return getPredicateFromSearchCriteria(userPathBuilder, new SearchCriteria("username", SearchOperation.EQUAL, username));
    }
}

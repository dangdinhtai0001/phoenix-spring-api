package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.base.repositories.UserRepository;
import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import com.phoenix.api.core.repository.AbstractBaseQueryDslRepository;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QFwUserGroup;
import com.phoenix.api.model.querydsl.QFwUserGroupMapping;
import com.phoenix.api.model.querydsl.QFwUserStatus;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository(BeanIds.BASE_USER_REPOSITORY_IMP)
@Log4j2
public class DefaultUserDetailsRepository extends AbstractBaseQueryDslRepository implements UserRepository {

    private final SQLQueryFactory queryFactory;

    protected DefaultUserDetailsRepository(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    @Override
    protected List<PathBuilder> getListPathBuilder() {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder userStatusPathBuilder = getPathBuilder(QFwUserStatus.class, QFwUserStatus.fwUserStatus);
        PathBuilder userGroupPathBuilder = getPathBuilder(QFwUserGroup.class, QFwUserGroup.fwUserGroup);
        PathBuilder userGroupMappingPathBuilder = getPathBuilder(QFwUserGroupMapping.class, QFwUserGroupMapping.fwUserGroupMapping);

        List<PathBuilder> pathBuilders = new LinkedList<>();

        pathBuilders.add(userPathBuilder);
        pathBuilders.add(userStatusPathBuilder);
        pathBuilders.add(userGroupPathBuilder);
        pathBuilders.add(userGroupMappingPathBuilder);

        return pathBuilders;
    }

    @Override
    public Optional<UserPrincipal> findUserPrincipalByUsername(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder userStatusPathBuilder = getPathBuilder(QFwUserStatus.class, QFwUserStatus.fwUserStatus);

        Expression[] expressions1 = getExpressions(userPathBuilder, "id", "username", "password", "hash_algorithm", "password_salt");
        Expression[] expressions2 = getExpressions(userStatusPathBuilder, "name");
        Expression[] expressions = mergeExpressions(expressions1, expressions2);

        SQLQuery query = queryFactory.select(expressions).from(userPathBuilder);

        join(JoinType.RIGHT, query, userPathBuilder, userStatusPathBuilder, "status_id", "id");

        SearchCriteria criteria = new SearchCriteria("username", SearchOperation.EQUAL, username);
        addWhereClause(query, userPathBuilder, criteria);

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

        log.info(query.getSQL().getSQL());

        return Optional.of(userPrincipal);
    }

    @Override
    public List findGroupIdsByUsername(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder userGroupPathBuilder = getPathBuilder(QFwUserGroup.class, QFwUserGroup.fwUserGroup);
        PathBuilder userGroupMappingPathBuilder = getPathBuilder(QFwUserGroupMapping.class, QFwUserGroupMapping.fwUserGroupMapping);

        Expression[] expressions = getExpressions(userGroupPathBuilder, "id");

        SQLQuery query = queryFactory.select(expressions).from(userPathBuilder);

        join(JoinType.LEFT, query, userPathBuilder, userGroupMappingPathBuilder, "id", "user_id");
        join(JoinType.LEFT, query, userGroupMappingPathBuilder, userGroupPathBuilder, "group_id", "id");

        SearchCriteria criteria = new SearchCriteria("username", SearchOperation.EQUAL, username);
        addWhereClause(query, userPathBuilder, criteria);

        log.info(query.getSQL().getSQL());

        List<Tuple> queryResult = query.fetch();

        return queryResult.stream().map(tuple -> tuple.get(0, Integer.class)).collect(Collectors.toList());
    }

    @Override
    public int updateRefreshTokenByUsername(String refreshToken, String username) {
        RelationalPath relationalPath = getRelationalPathBase(QFwUser.class, QFwUser.fwUser);

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

        log.info(query.getSQL().getSQL());

        Tuple queryResult = (Tuple) query.fetchOne();

        return Optional.ofNullable(queryResult.get(0, String.class));
    }

    //===========================================

    private Predicate getEqualsUsernamePredicate(String username) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        return getPredicateFromSearchCriteria(userPathBuilder, new SearchCriteria("username", SearchOperation.EQUAL, username));
    }
}

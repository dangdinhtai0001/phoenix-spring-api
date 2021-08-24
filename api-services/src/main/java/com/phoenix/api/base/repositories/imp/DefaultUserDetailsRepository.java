package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.base.repositories.UserRepository;
import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import com.phoenix.api.core.repository.AbstractQueryDslRepository;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QFwUserStatus;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultUserDetailsRepository extends AbstractQueryDslRepository implements UserRepository {

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

        List<PathBuilder> pathBuilders = new LinkedList<>();

        pathBuilders.add(userPathBuilder);
        pathBuilders.add(userStatusPathBuilder);

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
        Predicate predicate = getPredicateFromSearchCriteria(userPathBuilder, criteria);
        addWhereClause(query, predicate);

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

        System.out.println(query.getSQL().getSQL());

        return Optional.of(userPrincipal);
    }

    @Override
    public int updateRefreshTokenByUsername(String refreshToken, String username) {
        return 0;
    }

    @Override
    public Optional findUserProfileByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional findRefreshTokenByUsername(String username) {
        return Optional.empty();
    }
}

package com.phoenix.api.business.repository.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.AbstractBaseQueryDslRepository;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QProfile;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Repository(BeanIds.USER_REPOSITORY_IMP)
@Transactional
public class UserRepositoryImp extends AbstractBaseQueryDslRepository implements UserRepository {
    private final SQLQueryFactory queryFactory;

    protected UserRepositoryImp(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    @Override
    protected List<PathBuilder> getListPathBuilder() {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder profilePathBuilder = getPathBuilder(QProfile.class, QProfile.profile);

        List<PathBuilder> pathBuilders = new LinkedList<>();

        pathBuilders.add(userPathBuilder);
        pathBuilders.add(profilePathBuilder);

        return pathBuilders;
    }

    @Override
    public long countByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder profilePathBuilder = getPathBuilder(QProfile.class, QProfile.profile);

        SQLQuery query = queryFactory.select().from(userPathBuilder);

        query = join(JoinType.LEFT, query, userPathBuilder, profilePathBuilder, "id", "user_id");

        List<Predicate> predicates = getPredicateFromSearchCriteria(User.class, getListPathBuilder(), searchCriteriaList);

        addWhereClause(query, predicates);

        return query.fetchCount();
    }

    @Override
    public BasePagination findByCondition(List<SearchCriteria> searchCriteriaList, int pageOffset, int pageSize, OrderBy orderBy) {
        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, QFwUser.fwUser);
        PathBuilder profilePathBuilder = getPathBuilder(QProfile.class, QProfile.profile);

        Expression[] expressions1 = getExpressions(userPathBuilder, "id", "username", "password");
        Expression[] expressions2 = getExpressions(profilePathBuilder, "name", "date_of_birth", "phone_number", "gender", "avatar");
        Expression[] expressions = mergeExpressions(expressions1, expressions2);

        SQLQuery query = queryFactory.select(expressions).from(userPathBuilder);

        query = join(JoinType.LEFT, query, userPathBuilder, profilePathBuilder, "id", "user_id");

        List<Predicate> predicates = getPredicateFromSearchCriteria(User.class, getListPathBuilder(), searchCriteriaList);

        addWhereClause(query, predicates);
        addOrderBy(query, User.class, getListPathBuilder(), orderBy);

        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);

        return fetchWithPagination(pageRequest, query, User.class, "id", "username", "password", "name", "dateOfBirth", "phoneNumber", "gender", "avatar");
    }
}

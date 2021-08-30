package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.MenuEntity;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.business.model.User;
import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.OrderDirection;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.AbstractBaseJpaRepository;
import com.phoenix.api.core.repository.AbstractBaseQueryDslRepository;
import com.phoenix.api.model.querydsl.QFwMenu;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QProfile;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

@Repository(BeanIds.MENU_REPOSITORY_IMP)
public class MenuRepositoryImp extends AbstractBaseQueryDslRepository implements MenuRepository {

    private final SQLQueryFactory queryFactory;

    protected MenuRepositoryImp(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    @Override
    protected List<PathBuilder> getListPathBuilder() {
        List<PathBuilder> pathBuilders = new LinkedList<>();
        pathBuilders.add(getPathBuilder(QFwMenu.class, QFwMenu.fwMenu));
        return pathBuilders;
    }

    @Override
    public List findAll() {
        return findAll(null);
    }

    @Override
    public List findAll(List<SearchCriteria> searchCriteriaList) {
        PathBuilder menuPathBuilder = getPathBuilder(QFwMenu.class, QFwMenu.fwMenu);

        Expression[] expressions = getExpressions(menuPathBuilder, "id", "display_name", "path", "parent_id", "display_order", "description", "is_hidden", "icon");

        SQLQuery query = queryFactory.select(expressions).from(menuPathBuilder);

        List<Predicate> predicates = getPredicateFromSearchCriteria(User.class, getListPathBuilder(), searchCriteriaList);

        addWhereClause(query, predicates);

        OrderBy orderBy = new OrderBy(OrderDirection.ASC, "parent_id", "display_order");
        addOrderBy(query, menuPathBuilder, orderBy);

        //return query.fetch();
         List<Tuple> queryResult =  query.fetch();

         return parseResult(queryResult, MenuEntity.class,"id", "displayName", "path", "parentId");
    }
}

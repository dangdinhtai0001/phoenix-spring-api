package com.phoenix.api.core.repository;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.core.model.JoinType;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import com.phoenix.api.core.repository.specification.AbstractQueryDslRepository;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QProfile;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestQueryDsl extends AbstractQueryDslRepository {
    private final SQLQueryFactory queryFactory;

    protected TestQueryDsl(
            @Qualifier(BeanIds.SQL_QUERY_FACTORY) SQLQueryFactory queryFactory
    ) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    @Test
    public void testQueryDslInSingleTable() throws ClassNotFoundException {
        String className = "com.phoenix.api.model.querydsl.QFwUser";
        SearchCriteria criteria = new SearchCriteria("id", SearchOperation.LESS_THAN, 10);
        SearchCriteria criteria1 = new SearchCriteria("username", SearchOperation.IN, "admin", "user");
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        searchCriteriaList.add(criteria);
        searchCriteriaList.add(criteria1);

        // --------------------------------------

        String tableName = getTableName(QFwUser.fwUser);
        PathBuilder pathBuilder = getPathBuilder(className, tableName);
        Expression[] expressions = getExpressions(pathBuilder, "id", "username", "password");

        SQLQuery<Tuple> query = createSelectQuery(expressions, pathBuilder);
        List<Predicate> predicates = getPredicateFromSearchCriteria(pathBuilder, searchCriteriaList);
        addWhereClause(query, predicates);

        List<Tuple> result = query.fetch();

        for (Tuple record : result) {
            System.out.printf("id: %d, username: %s, password: %s%n",
                    record.get(0, Long.class),
                    record.get(1, String.class),
                    record.get(2, String.class)
            );
        }
    }

    @Test
    public void testQueryDslInMultiTable() throws ClassNotFoundException {
        SearchCriteria criteria = new SearchCriteria("id", SearchOperation.LESS_THAN, 10);
        SearchCriteria criteria1 = new SearchCriteria("username", SearchOperation.IN, "admin", "user");
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        searchCriteriaList.add(criteria);
        searchCriteriaList.add(criteria1);

        // --------------------------------------

        String userTableName = getTableName(QFwUser.fwUser);
        String profileTableName = getTableName(QProfile.profile);

        PathBuilder userPathBuilder = getPathBuilder(QFwUser.class, userTableName);
        PathBuilder profilePathBuilder = getPathBuilder(QProfile.class, profileTableName);

        Expression[] expressions1 = getExpressions(userPathBuilder, "id", "username", "password");
        Expression[] expressions2 = getExpressions(profilePathBuilder, "name");
        Expression[] expressions = mergeExpressions(expressions1, expressions2);

        SQLQuery<Tuple> query = queryFactory
                .select(expressions)
                .from(userPathBuilder);

        query = join(JoinType.LEFT, query, userPathBuilder, profilePathBuilder, "id", "user_id");

        List<Predicate> predicates = getPredicateFromSearchCriteria(userPathBuilder, searchCriteriaList);
        addWhereClause(query, predicates);

        System.out.println(query.getSQL().getSQL());

        List<Tuple> result = query.fetch();

        for (Tuple record : result) {
            System.out.printf("id: %d, username: %s, password: %s, name:%s%n",
                    record.get(0, Long.class),
                    record.get(1, String.class),
                    record.get(2, String.class),
                    record.get(3, String.class)
            );
        }
    }
}

package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.model.querydsl.QFwMenu;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class MenuRepositoryImpTest {
    @Autowired
    @Qualifier(BeanIds.SQL_QUERY_FACTORY)
    private SQLQueryFactory queryFactory;

    @Test
    @Transactional
    public void testExpression() {
        PathBuilder pathBuilder = new PathBuilder(QFwMenu.class, "fw_menu");
        SQLQuery query = queryFactory.select(QFwMenu.fwMenu.id, QFwMenu.fwMenu.path, QFwMenu.fwMenu.userGroupsRequired)
                .from(pathBuilder)
                .where(Expressions.booleanTemplate(
                        "JSON_CONTAINS(user_groups_required, '[2]', '$')", QFwMenu.fwMenu.userGroupsRequired))
                ;

        System.out.println("===========================================");
        System.out.println(query.getSQL().getSQL());
        List<Tuple> queryResult = query.fetch();

        for (Tuple tuple : queryResult){
            System.out.println(tuple);
        }
        System.out.println("===========================================");
    }
}
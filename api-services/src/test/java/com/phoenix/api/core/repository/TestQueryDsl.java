package com.phoenix.api.core.repository;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.model.querydsl.QFwUser;
import com.phoenix.api.model.querydsl.QProfile;
import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestQueryDsl {

    @Autowired
    @Qualifier(BeanIds.SQL_QUERY_FACTORY)
    SQLQueryFactory queryFactory;

    @Test
    public void testQueryDsl() {

        QFwUser user = new QFwUser("user", "phoenix-v2");
        QProfile profile = new QProfile("profile", "phoenix-v2");

        System.out.printf("User schema's name: %s, table's name: %s%n", user.getSchemaName(), user.getTableName());

        List<Tuple> result = queryFactory
                .select(user.id, user.username, user.password, profile.name)
                .from(user)
                .leftJoin(profile).on(user.id.eq(profile.userId))
                .where(user.id.lt(10L))
                .orderBy(profile.name.asc())
                .fetch();

        for (Tuple record : result) {
            System.out.printf("id: %d, username: %s, password: %s, name: %s%n",
                    record.get(0, Long.class),
                    record.get(1, String.class),
                    record.get(2, String.class),
                    record.get(3, String.class)
            );
        }
    }
}

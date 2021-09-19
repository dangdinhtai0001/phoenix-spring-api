package com.phoenix.base.repository.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.querydsl.QFwException;
import com.phoenix.core.model.query.ExpressionType;
import com.phoenix.core.model.query.QueryExpression;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.sql.RelationalPathBase;
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
@ActiveProfiles(value = "dev")
class ExceptionRepositoryImpTest {
    @Autowired
    @Qualifier(value = BeanIds.EXCEPTION_REPOSITORY_IMP)
    private ExceptionRepositoryImp exceptionRepositoryImp;

    @Autowired
    @Qualifier(value = BeanIds.SQL_QUERY_FACTORY)
    private SQLQueryFactory queryFactory;

    @Test
    @Transactional
    public void testExpressionInWhereClause() {
        String[] columns = {"id", "code_", "message_", "httpCode"};
        String[] fields = {"id", "code", "message", "httpCode"};

        RelationalPathBase<QFwException> relationalPathBase = exceptionRepositoryImp.getRelationalPathBase();
        Path<?>[] paths = exceptionRepositoryImp.getPaths(relationalPathBase, columns);
        SQLQuery query = exceptionRepositoryImp.createSelectQuery(relationalPathBase, paths);

        QueryExpression expression = new QueryExpression("id > 1 and  id < 6",
                ExpressionType.BOOLEAN, QFwException.fwException.id);

        exceptionRepositoryImp.addWhereClause(query, expression);

        List<Tuple> queryResult = query.fetch();

        for (Tuple tuple : queryResult) {
            System.out.println(tuple);
        }


    }
}
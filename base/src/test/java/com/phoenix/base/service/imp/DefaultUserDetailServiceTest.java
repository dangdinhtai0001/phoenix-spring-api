package com.phoenix.base.service.imp;

import com.phoenix.base.constant.BeanIds;
import com.phoenix.base.model.querydsl.QFwUser;
import com.phoenix.base.repository.imp.DefaultUserDetailsRepository;
import com.phoenix.common.structure.Tuple;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.sql.RelationalPathBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "dev")
class DefaultUserDetailServiceTest {
    @Autowired
    @Qualifier(value = BeanIds.BASE_USER_REPOSITORY_IMP)
    private DefaultUserDetailsRepository defaultUserDetailsRepository;

    @Test
    public void testFIndUsers() {
        Tuple tuple = defaultUserDetailsRepository.getRelationalPathMap();

        RelationalPathBase<QFwUser> userPath = tuple.get(QFwUser.fwUser.getTableName(), RelationalPathBase.class);

        PathMetadata metadata = userPath.getMetadata();

        System.out.println(metadata);
    }

}
package com.phoenix.api.business.repository.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.*;
import com.phoenix.api.model.querydsl.QFwUser;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class UserRepositoryImpTest {

    @Autowired
    @Qualifier(BeanIds.USER_REPOSITORY_IMP)
    private UserRepositoryImp userRepository;

    @Test
    public void testCount() throws SearchCriteriaException {
        SearchCriteria criteria = new SearchCriteria("id", SearchOperation.LESS_THAN, 10);
        SearchCriteria criteria1 = new SearchCriteria("username", SearchOperation.IN, "admin", "user");
        SearchCriteria criteria2 = new SearchCriteria("name", SearchOperation.IN, "admin", "user");
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        searchCriteriaList.add(criteria);
        searchCriteriaList.add(criteria1);

        Long count = userRepository.countByCondition(searchCriteriaList);

        System.out.println(count);
    }

    @Test
    public void testFind() {
        List<String> keys = new LinkedList<>();
        keys.add("name");
        keys.add("username");
        OrderBy orderBy = new OrderBy(keys, OrderDirection.DESC);

        SearchCriteria criteria = new SearchCriteria("id", SearchOperation.LESS_THAN, 10);
        SearchCriteria criteria1 = new SearchCriteria("username", SearchOperation.IN, "admin", "user");
        SearchCriteria criteria2 = new SearchCriteria("name", SearchOperation.LIKE, "%Cyb%");
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        searchCriteriaList.add(criteria);
//        searchCriteriaList.add(criteria1);
//        searchCriteriaList.add(criteria2);


        BasePagination basePagination = userRepository.findByCondition(searchCriteriaList, 0, 10, orderBy);

        System.out.println(basePagination);

        System.out.println("-----------------");

        List list = basePagination.getItems();

        for (Object obj : list) {
            System.out.println(obj);
        }
    }


}
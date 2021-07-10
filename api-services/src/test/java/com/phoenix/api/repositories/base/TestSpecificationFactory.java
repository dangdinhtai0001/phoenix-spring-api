/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.api.repositories.base;

import com.phoenix.api.base.repositories.GenericSpecificationsBuilder;
import com.phoenix.api.base.repositories.SpecificationFactory;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.repositories.common.MenuRepositoryImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@SpringBootTest
public class TestSpecificationFactory {


    @Autowired
    private MenuRepositoryImp menuRepositoryImp;

    @Test
    public void testFindWithPredicate() throws Exception {
        GenericSpecificationsBuilder<MenuEntity> builder = new GenericSpecificationsBuilder<>();
        SpecificationFactory<MenuEntity> specificationFactory = new SpecificationFactory<>();
        builder.with(specificationFactory.isGreaterThan("id", 1));
        Specification specification = builder.build();

        System.out.println(menuRepositoryImp.findBySpecification(specification));
    }

    @Test
    public void testFindWithPredicateAndPageRequest() throws Exception {
        GenericSpecificationsBuilder<MenuEntity> builder = new GenericSpecificationsBuilder<>();
        SpecificationFactory<MenuEntity> specificationFactory = new SpecificationFactory<>();

//        builder.with(specificationFactory.isGreaterThan("id", 1));
        Specification specification = builder.build();

        PageRequest pageRequest = PageRequest.of(0, 1);

        PagedListHolder<MenuEntity> page = menuRepositoryImp.findBySpecificationAndPageRequest(specification, pageRequest);
    }
}

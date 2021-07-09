package com.phoenix.api.repositories.base;

import com.phoenix.api.base.repositories.GenericSpecificationsBuilder;
import com.phoenix.api.base.repositories.SpecificationFactory;
import com.phoenix.api.entities.common.MenuEntity;
import com.phoenix.api.repositories.common.MenuRepositoryImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

@SpringBootTest
public class TestSpecificationFactory {
    @Autowired
    private SpecificationFactory specificationFactory;

    @Autowired
    private MenuRepositoryImp menuRepositoryImp;

    @Test
    public void testFindWithPredicate() throws Exception {
        GenericSpecificationsBuilder<MenuEntity> builder = new GenericSpecificationsBuilder<>();
        builder.with(specificationFactory.isEqual("name", "menu_1"));
        Specification specification = builder.build();

        System.out.println(menuRepositoryImp.findBySpecification(specification));
    }
}

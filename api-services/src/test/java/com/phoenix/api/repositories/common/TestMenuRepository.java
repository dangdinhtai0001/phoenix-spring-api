package com.phoenix.api.repositories.common;

import com.phoenix.api.entities.common.MenuEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TestMenuRepository {

    @Autowired
    private MenuRepositoryImp menuRepositoryImp;

    @Test
    public void testFindAll() {
        Iterable<MenuEntity> iterator = menuRepositoryImp.findAll();

        for (MenuEntity entity : iterator) {
            System.out.println(entity.toString());
        }
    }

    @Test
    public void testFindById() throws Exception {
        Optional<MenuEntity> optional = menuRepositoryImp.findById(10L);

        System.out.println(optional.orElse(null));
    }

}

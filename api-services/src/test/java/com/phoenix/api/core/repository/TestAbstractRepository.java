package com.phoenix.api.core.repository;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.ExceptionRepositoryImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestAbstractRepository {
    @Autowired
    @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP)
    private ExceptionRepositoryImp exceptionRepositoryImp;

    @Test
    public void testFindAll() {
        List<ExceptionEntity> list = exceptionRepositoryImp.getJpaRepository().findAll();
        System.out.println(list);
    }

    @Test
    public void testFindByAttribute() {
        Optional<ExceptionEntity> optional = exceptionRepositoryImp.getJpaRepository().findById(1L);
        System.out.println(optional.orElse(null));
    }
}

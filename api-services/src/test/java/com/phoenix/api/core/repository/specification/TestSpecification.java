package com.phoenix.api.core.repository.specification;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.ExceptionRepositoryImp;
import com.phoenix.api.core.model.SearchOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestSpecification {
    @Autowired
    @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP)
    private ExceptionRepositoryImp exceptionRepositoryImp;

    @Test
    public void testFindWithLikeSpec() {
        Specification<ExceptionEntity> specification = Specifications.<ExceptionEntity>and()
                .like("code", "%A%")
                .build();

        System.out.println(exceptionRepositoryImp.findAll(specification));
    }

    @Test
    public void testFindWithEqualSpec() {
        Specification<ExceptionEntity> specification = Specifications.<ExceptionEntity>and()
                .eq("code", "AUTH_001")
                .build();

        System.out.println(exceptionRepositoryImp.findAll(specification));
    }

    @Test
    public void testFindWithMultiSpec() {
        Specification<ExceptionEntity> specification = Specifications.<ExceptionEntity>and()
                .like("code", "%2%")
                .gt("id", 1)
                .build()
                .or(Specifications.<ExceptionEntity>and()
                        .like("code", "%3%")
                        .gt("id", 1)
                        .build());

        System.out.println(exceptionRepositoryImp.findAll(specification));
    }
}

package com.phoenix.api.core.repository.specification;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.ExceptionRepositoryImp;
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
                .like( "code_", "%1%")
                .build();

        System.out.println(exceptionRepositoryImp.findAll(specification));
    }
}

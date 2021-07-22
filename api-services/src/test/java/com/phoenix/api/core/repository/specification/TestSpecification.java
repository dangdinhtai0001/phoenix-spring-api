package com.phoenix.api.core.repository.specification;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.ExceptionRepositoryImp;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void testFindWithSearchCriteria() {
        List<SearchCriteria> conditions = new LinkedList<>();

        conditions.add(new SearchCriteria("code", SearchOperation.LIKE, "%2%"));
        conditions.add(new SearchCriteria("id", SearchOperation.GREATER_THAN, 1));


        PredicateBuilder<ExceptionEntity> predicate = new PredicateBuilder<>(Predicate.BooleanOperator.AND);

        for (SearchCriteria criteria : conditions) {
            if (criteria.getSearchOperation() == SearchOperation.LIKE) {
                predicate.like(criteria.getKey(), String.valueOf(criteria.getArguments().get(0)));
            }

            if (criteria.getSearchOperation() == SearchOperation.GREATER_THAN) {
                predicate.gt(criteria.getKey(), (Comparable<?>) criteria.getArguments().get(0));
            }
        }


        Specification<ExceptionEntity> specification = predicate.build();

        System.out.println(exceptionRepositoryImp.findAll(specification));
    }
}

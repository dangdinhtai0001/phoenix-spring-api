package com.phoenix.api.core.repository;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.imp.ExceptionRepositoryImp;
import com.phoenix.api.business.repository.imp.UserRepositoryImp;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.OrderByRequest;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.common.structure.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class TestAbstractRepository {
    @Autowired
    @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP)
    private ExceptionRepositoryImp exceptionRepositoryImp;

    @Autowired
    @Qualifier(BeanIds.USER_REPOSITORY_IMP)
    private UserRepositoryImp userRepositoryImp;

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

    @Test
    public void testNativeQuery() throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        List<Object[]> list = exceptionRepositoryImp.executeNativeQuery("select id, code_, message_ from fw_exception");

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("id", Long.class));
        params.add(new Pair<>("code", String.class));
        params.add(new Pair<>("message", String.class));
//        params.add(new Pair<>("httpCode", Integer.class));

        System.out.println(exceptionRepositoryImp.parseResult(list, params, ExceptionEntity.class));
    }

    @Test
    public void testFindWithSort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "httpCode", "code");
        List<ExceptionEntity> entities = exceptionRepositoryImp.findAll(sort);

        for (ExceptionEntity entity : entities) {
            System.out.println(entity);
        }
    }

    @Test
    public void testFindWithPage() {
        Page<ExceptionEntity> entities = exceptionRepositoryImp.findAll(PageRequest.of(2, 1));
        BasePagination<ExceptionEntity> pagination = new BasePagination<>(entities);

        for (ExceptionEntity entity : pagination.getItems()) {
            System.out.println(entity);
        }
    }

    @Test
    public void testNativeQueryWithPage() throws Exception {
        List<SearchCriteria> searchCriteriaList = new LinkedList<>();
        int pageOffset = 1;
        int pageSize = 2;

        BasePagination users = userRepositoryImp.findByCondition(searchCriteriaList, pageOffset, pageSize, null);

        System.out.println(users.getItems());
    }

    @Test
    public void testCreateOrderBy() throws Exception {
        List<String> keys = new LinkedList<>();
        keys.add("id");
        keys.add("code_");

        OrderByRequest orderByRequest = new OrderByRequest(keys, "ASC");

        OrderBy orderBy = orderByRequest.getOderBy();
        String orderByClause = userRepositoryImp.getOderByClause(orderBy);

        System.out.println(orderByClause);
    }
}

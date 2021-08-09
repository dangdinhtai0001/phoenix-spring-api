package com.phoenix.api.business.repository.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.BasePagination;
import com.phoenix.api.core.model.OrderBy;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.AbstractNativeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository(BeanIds.USER_REPOSITORY_IMP)
public class UserRepositoryImp extends AbstractNativeRepository implements UserRepository {

    public UserRepositoryImp(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public long countByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException {
        String condition = getConditionClauseFromSearchCriteria(searchCriteriaList);
        String sql = "select count(*) from profile p right join fw_user fu on fu.id = p.user_id where " + condition;
        List<Object> conditions = getParameterFromSearchCriteria(searchCriteriaList);

        Object queryResult = executeNativeQuery(sql, conditions.toArray()).get(0);

        return Long.parseLong(String.valueOf(queryResult));
    }

    @Override
    public BasePagination findByCondition(List<SearchCriteria> searchCriteriaList, int pageOffset, int pageSize, OrderBy oderBy)
            throws SearchCriteriaException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        //Get Condition String from list SearchCriteria
        String condition = getConditionClauseFromSearchCriteria(searchCriteriaList);

        // Get order by clause
        String order = getOderByClause(oderBy);

        // Define sql and total sql
        String sql = "select fu.id, p.name, p.date_of_birth, p.gender, p.phone_number, p.avatar, fu.username " +
                "from fw_user fu left join profile p on fu.id = p.USER_ID where " + condition + order;
        String totalSql = "select count(*) from fw_user fu left join profile p on fu.id = p.USER_ID where " + condition;

        // Get list param for query
        List<Object> conditions = getParameterFromSearchCriteria(searchCriteriaList);

        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
//        return parseResult(queryResult, params, User.class);
        return executeNativeQuery(User.class, pageRequest, totalSql, sql, conditions.toArray());
    }
}

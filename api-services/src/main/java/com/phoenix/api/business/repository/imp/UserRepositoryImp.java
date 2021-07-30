package com.phoenix.api.business.repository.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.repository.AbstractNativeRepository;
import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.ReflectionUtil;
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
    public List<User> findByCondition(List<SearchCriteria> searchCriteriaList) throws SearchCriteriaException,
            NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String condition = getConditionClauseFromSearchCriteria(searchCriteriaList);
        String sql = "select fu.id, p.name, p.date_of_birth, p.gender, p.phone_number, p.avatar, fu.username " +
                "from fw_user fu left join profile p on fu.id = p.USER_ID where " + condition;
        List<Object> conditions = getParameterFromSearchCriteria(searchCriteriaList);

        List<Object[]> queryResult = executeNativeQuery(sql, conditions.toArray());

        List<Pair<String, Class>> params = ReflectionUtil.getFieldAsPairList(User.class,
                "id", "name", "dateOfBirth", "gender", "phoneNumber", "avatar", "username");

        return parseResult(queryResult, params, User.class);
    }
}

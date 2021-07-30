package com.phoenix.api.business.services.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.business.services.UserService;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import com.phoenix.api.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service(BeanIds.USER_SERVICES)
public class UserServiceImp extends AbstractBaseService implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(
            @Qualifier(BeanIds.USER_REPOSITORY_IMP) UserRepository userRepository,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities) {
        super(exceptionEntities);
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByCondition(List<SearchCriteriaRequest> listConditionRequests, int pageOffset, int pageSize)
            throws SearchCriteriaException, NoSuchFieldException, InvocationTargetException, IllegalAccessException,
            InstantiationException, NoSuchMethodException {
        List<SearchCriteria> conditions = getListOfSearchCriteria(listConditionRequests);
        return userRepository.findByCondition(conditions);
    }

    @Override
    public long countByCondition(List<SearchCriteriaRequest> listConditionRequests) throws SearchCriteriaException {
        List<SearchCriteria> conditions = getListOfSearchCriteria(listConditionRequests);
        return userRepository.countByCondition(conditions);
    }
}

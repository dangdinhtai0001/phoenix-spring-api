package com.phoenix.api.business.services.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.business.services.UserService;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import com.phoenix.api.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<User> findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize) {
        return null;
    }

    @Override
    public long countByCondition(List<SearchCriteriaRequest> listConditionRequests) {
        List<SearchCriteria> conditions = listConditionRequests.stream().map(SearchCriteriaRequest::getSearchCriteria).collect(Collectors.toList());
        String condition = getConditionClauseFromSearchCriteria(conditions);
        return userRepository.countByCondition(" where " + condition);
    }
}

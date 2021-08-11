package com.phoenix.api.business.services.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.FilterMetadataRepository;
import com.phoenix.api.business.model.User;
import com.phoenix.api.business.repository.UserRepository;
import com.phoenix.api.business.services.UserService;
import com.phoenix.api.core.annotation.ApplicationAuthorization;
import com.phoenix.api.core.annotation.ApplicationResource;
import com.phoenix.api.core.exception.ApplicationException;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.*;
import com.phoenix.api.core.service.AbstractBaseServiceWithFilterHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

@Service(BeanIds.USER_SERVICES)
@ApplicationAuthorization
@ApplicationResource(description = "User services")
@Log4j2
public class UserServiceImp extends AbstractBaseServiceWithFilterHelper implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(
            @Qualifier(BeanIds.USER_REPOSITORY_IMP) UserRepository userRepository,
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.FILTER_METADATA_REPOSITORY_IMP) FilterMetadataRepository filterMetadataRepository) {
        super(exceptionEntities, filterMetadataRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Object create(Object entity) throws ApplicationException {
        return null;
    }

    @Override
    public Object update(Object entity) throws ApplicationException {
        return null;
    }

    @Override
    public void delete(Object entity) throws ApplicationException {

    }

    @Override
    public void deleteAll(Collection entities) throws ApplicationException {

    }

    @Override
    public BasePagination findByCondition(List<SearchCriteriaRequest> listConditionRequests, int pageOffset, int pageSize,
                                          List<String> orderByKeys, String direction)
            throws ApplicationException {
        List<SearchCriteria> conditions = getListOfSearchCriteria(listConditionRequests);
        OrderBy orderBy = new OrderByRequest(orderByKeys, direction).getOderBy();

        try {
            orderBy.setKeys(mapBusinessObjectField2TableColumn(orderBy.getKeys(), User.class));
            conditions = mapBusinessObjectSearchCriteria2Table(conditions, User.class);
            return userRepository.findByCondition(conditions, pageOffset, pageSize, orderBy);
        } catch (SearchCriteriaException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new ApplicationException(e.getMessage(), "");
        }
    }

    @Override
    public long countByCondition(List<SearchCriteriaRequest> listConditionRequests) throws ApplicationException {
        List<SearchCriteria> conditions = getListOfSearchCriteria(listConditionRequests);

        try {
            conditions = mapBusinessObjectSearchCriteria2Table(conditions, User.class);
            return userRepository.countByCondition(conditions);
        } catch (SearchCriteriaException e) {
            log.warn(e.getMessage(), e);
            throw new ApplicationException(e.getMessage(), "");
        }
    }
}

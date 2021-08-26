package com.phoenix.api.base.service.imp;

import com.google.gson.Gson;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.MenuEntity;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.base.service.MenuService;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import com.phoenix.api.core.repository.specification.PredicateBuilder;
import com.phoenix.api.core.repository.specification.Specifications;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.api.core.service.AbstractJpaBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service(BeanIds.MENU_SERVICES)
@Log4j2
public class MenuServiceImp extends AbstractJpaBaseService<MenuEntity> implements MenuService {
    private final MenuRepository menuRepository;

    protected MenuServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.MENU_REPOSITORY_IMP) MenuRepository menuRepository
    ) {
        super(exceptionEntities);
        this.menuRepository = menuRepository;
    }

    @Override
    public List findAll() {
        UsernamePasswordAuthenticationToken token = getCurrentSecurityToken();

        List<String> list = token.getAuthorities().stream().map(a -> "%\"" + a + "\"%").collect(Collectors.toList());
        List<SearchCriteria> searchCriteriaList = new LinkedList<>();
        for (String group : list) {
            searchCriteriaList.add(new SearchCriteria("userGroupsRequired", SearchOperation.LIKE, group));
        }

        return menuRepository.findAll(getPredicateBuilderFromSearchCriteria(searchCriteriaList, Predicate.BooleanOperator.OR).build());
    }
}

package com.phoenix.api.base.service.imp;

import com.google.gson.Gson;
import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.MenuEntity;
import com.phoenix.api.base.model.MenuWrapper;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.base.service.MenuService;
import com.phoenix.api.core.model.ExpressionType;
import com.phoenix.api.core.model.QueryExpression;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.api.model.querydsl.QFwMenu;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service(BeanIds.MENU_SERVICES)
@Log4j2
public class MenuServiceImp extends AbstractBaseService implements MenuService {
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
        Gson gson = new Gson();
        List<Integer> authorities = token.getAuthorities().stream()
                .map(t -> Integer.parseInt(String.valueOf(t)))
                .collect(Collectors.toList());
        String s = gson.toJson(authorities);

        QueryExpression expression = new QueryExpression(String.format("JSON_CONTAINS('%s', user_groups_required, '$')", s),
                ExpressionType.BOOLEAN, QFwMenu.fwMenu.userGroupsRequired);

        List<SearchCriteria> searchCriteriaList = new LinkedList<>();
        List<QueryExpression> expressions = new LinkedList<>();
        expressions.add(expression);

        List<MenuEntity> entities = menuRepository.findAll(searchCriteriaList, expressions);

        return buildTree(entities);
    }

    //Establish tree structure
    private List<MenuWrapper> buildTree(List<MenuEntity> list) {
        List<MenuWrapper> treeMenus = new ArrayList<>();
        for (MenuEntity entity : getRootNode(list)) {
            MenuWrapper menuNode = buildChildTree(entity, list);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //Recursion, building subtree structure
    private MenuWrapper buildChildTree(MenuEntity pNode, List<MenuEntity> entities) {
        List<MenuWrapper> childMenus = new ArrayList<>();
        MenuWrapper wrapper = new MenuWrapper(pNode);
        for (MenuEntity menuNode : entities) {
            if (menuNode.getParentId() != null && menuNode.getParentId() == Integer.parseInt(String.valueOf(pNode.getId()))) {
                menuNode.setPath(pNode.getPath() + menuNode.getPath());
                childMenus.add(buildChildTree(menuNode, entities));
            }
        }
        wrapper.setChildren(childMenus);
        return wrapper;
    }

    //Get root node
    private List<MenuEntity> getRootNode(List<MenuEntity> list) {
        return list.stream().filter(a -> a.getParentId() == null).collect(Collectors.toList());
    }
}

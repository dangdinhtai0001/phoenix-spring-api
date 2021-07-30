package com.phoenix.api.business.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.services.UserService;
import com.phoenix.api.core.controller.AbstractDefaultController;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RestController("UserController")
@RequestMapping("/user")
public class UserController extends AbstractDefaultController {

    private final UserService userService;

    public UserController(
            @Qualifier(BeanIds.USER_SERVICES) UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity create(Object entity) {
        return sendResponse(null);
    }

    @Override
    public ResponseEntity createAll(Collection entities) {
        return null;
    }

    @Override
    public ResponseEntity update(Object entity) {
        return null;
    }

    @Override
    public ResponseEntity delete(Object entity) {
        return null;
    }

    @Override
    public ResponseEntity deleteAll(Collection entities) {
        return null;
    }

    @Override
    public ResponseEntity findByCondition(@RequestBody(required = false) List<SearchCriteriaRequest> listConditionRequests,
                                          @RequestParam(name = "offset") int pageOffset,
                                          @RequestParam(name = "size") int pageSize) throws SearchCriteriaException,
            NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return sendResponse(userService.findByCondition(listConditionRequests, pageOffset, pageSize));
    }

    @Override
    public ResponseEntity countByCondition(@RequestBody(required = false) LinkedList<SearchCriteriaRequest> listConditionRequests) throws SearchCriteriaException {
        return sendResponse(userService.countByCondition(listConditionRequests));
    }
}

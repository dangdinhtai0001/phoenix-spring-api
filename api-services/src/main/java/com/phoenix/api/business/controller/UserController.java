package com.phoenix.api.business.controller;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.business.services.UserService;
import com.phoenix.api.core.controller.AbstractBaseController;
import com.phoenix.api.core.controller.DefaultController;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteriaRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RestController("UserController")
@RequestMapping("/user")
public class UserController extends AbstractBaseController implements DefaultController {

    private final UserService userService;

    public UserController(
            @Qualifier(BeanIds.USER_SERVICES) UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity create(Object entity) {
        return sendResponse(null);
    }

    @Override
    @PostMapping("/create-all")
    public ResponseEntity createAll(Collection entities) {
        return null;
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity update(Object entity) {
        return null;
    }

    @Override
    @PostMapping("/delete")
    public ResponseEntity delete(Object entity) {
        return null;
    }

    @Override
    @PostMapping("/delete-all")
    public ResponseEntity deleteAll(Collection entities) {
        return null;
    }

    @Override
    @GetMapping("/find-by")
    public ResponseEntity findByCondition(@RequestBody(required = false) List<SearchCriteriaRequest> listConditionRequests,
                                          @RequestParam(name = "offset") int pageOffset,
                                          @RequestParam(name = "size") int pageSize) throws SearchCriteriaException,
            NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        return sendResponse(userService.findByCondition(listConditionRequests, pageOffset, pageSize));
    }

    @Override
    @GetMapping("/count")
    public ResponseEntity countByCondition(@RequestBody(required = false) LinkedList<SearchCriteriaRequest> listConditionRequests) throws SearchCriteriaException {
        return sendResponse(userService.countByCondition(listConditionRequests));
    }
}

package com.phoenix.api.core.controller;

import com.phoenix.api.core.model.SearchCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

public abstract class AbstractCrudController extends AbstractBaseController implements CrudController {

    @Override
    @PostMapping("/create")
    public abstract ResponseEntity create(Object entity);

    @Override
    @PostMapping("/create-all")
    public abstract ResponseEntity createAll(Collection entities);

    @Override
    @PostMapping("/update")
    public abstract ResponseEntity update(Object entity);

    @Override
    @PostMapping("/delete")
    public abstract ResponseEntity delete(Object entity);

    @Override
    @PostMapping("/delete-all")
    public abstract ResponseEntity deleteAll(Collection entities);

    @Override
    @GetMapping("/find-by")
    public abstract ResponseEntity findByCondition(List<SearchCriteria> conditions, int pageOffset, int pageSize);

    @Override
    @GetMapping("/count")
    public abstract ResponseEntity countByCondition(List<SearchCriteria> conditions);
}

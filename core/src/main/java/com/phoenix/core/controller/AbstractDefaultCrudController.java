package com.phoenix.core.controller;

import com.phoenix.core.exception.ApplicationException;
import com.phoenix.core.model.query.SearchCriteriaRequest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDefaultCrudController extends AbstractCoreController implements DefaultCrudController {
    
    public abstract ResponseEntity create(Object entity) ;

    
    public abstract ResponseEntity createAll(Collection entities) ;

    
    public abstract ResponseEntity update(Object entity) ;

    
    public abstract ResponseEntity delete(Object entity) ;

    
    public abstract ResponseEntity deleteAll(Collection entities) ;

    
    public abstract ResponseEntity findByCondition(List<SearchCriteriaRequest> conditions, int pageOffset, int pageSize, List<String> orderByKeys, String direction) throws ApplicationException ;

    
    public abstract ResponseEntity countByCondition(LinkedList<SearchCriteriaRequest> conditions) throws ApplicationException ;
}

package com.phoenix.api.core.controller;

import com.phoenix.api.core.entity.BaseEntity;

import java.io.Serializable;

public abstract class AbstractCrudController<T extends BaseEntity<ID>, ID extends Serializable>
        extends AbstractBaseController implements CrudController {
}

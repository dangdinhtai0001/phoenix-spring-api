package com.phoenix.api.core.controller;

import com.phoenix.api.core.entity.BaseEntity;

public abstract class AbstractCrudController<T extends BaseEntity<ID>, ID>
        extends AbstractBaseController implements CrudController {
}

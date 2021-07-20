package com.phoenix.api.core.service;

import com.phoenix.api.core.entity.BaseEntity;

import java.io.Serializable;

public interface Service<T extends BaseEntity<ID>, ID extends Serializable> {

}

package com.phoenix.api.business.repository;

public interface UserRepository {
    long countByCondition(String condition);
}

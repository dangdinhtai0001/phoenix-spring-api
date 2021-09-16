package com.phoenix.base.repository;

import com.phoenix.core.model.DefaultException;

import java.util.List;

public interface ExceptionRepository {
    <T extends DefaultException> List<T> findAll();
}

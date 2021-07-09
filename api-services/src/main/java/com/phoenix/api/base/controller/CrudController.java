/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 9:35 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 2:07 PM
 */

package com.phoenix.api.base.controller;

import com.phoenix.api.base.entities.BaseEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @param <T> Đối tượng Entity để map với bảng trong csdl
 */
public interface CrudController<T extends BaseEntity> {
    ResponseEntity add(Map payload) throws Exception;

    ResponseEntity update(Map payload) throws Exception;

    ResponseEntity remove(Map payload) throws Exception;

    ResponseEntity findAll();

    ResponseEntity findById(Long id) throws Exception;
}

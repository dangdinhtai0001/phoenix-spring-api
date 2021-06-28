/*
 * @Author Đặng Đình Tài
 * @Date 6/28/21, 2:07 PM
 */

package com.phoenix.api.controller.base;

import com.phoenix.api.entities.base.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

public interface BaseController<T extends BaseEntity> {
    ResponseEntity add(Map payload) throws Exception;

    ResponseEntity update(Map payload) throws Exception;

    ResponseEntity remove(Map payload) throws Exception;

    ResponseEntity findAll();

    ResponseEntity findById(Long id) throws Exception;
}

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
    ResponseEntity add(Map payload);

    ResponseEntity update(Map payload);

    ResponseEntity remove(Map payload);

    ResponseEntity findAll();

    ResponseEntity findById();
}

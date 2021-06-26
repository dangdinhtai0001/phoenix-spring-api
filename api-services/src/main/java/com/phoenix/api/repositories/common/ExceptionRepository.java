/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 11:11 AM
 */

package com.phoenix.api.repositories.common;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.common.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = BeanIds.EXCEPTION_REPOSITORY)
public interface ExceptionRepository extends JpaRepository<ExceptionEntity, Long> {
    List<ExceptionEntity> findAll();
}

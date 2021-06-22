/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 9:07 PM
 */

package com.phoenix.api.repositories.auth;

import com.phoenix.api.entities.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}

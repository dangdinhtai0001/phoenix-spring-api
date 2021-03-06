/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 9:07 PM
 */

package com.phoenix.api.repositories.auth;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.constant.DatabaseConstant;
import com.phoenix.api.entities.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository(value = BeanIds.USER_REPOSITORY)
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = DatabaseConstant.FW_USER_UPDATE_REFRESH_TOKEN_BY_USERNAME, nativeQuery = true)
    int updateRefreshTokenByUsername(String refreshToken, String username);
}

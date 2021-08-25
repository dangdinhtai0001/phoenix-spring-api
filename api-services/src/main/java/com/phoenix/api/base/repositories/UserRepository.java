package com.phoenix.api.base.repositories;

import com.phoenix.api.base.model.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    /**
     * @param username Tài khoản cần tìm kiếm
     * @return Optional của UserPrincipal có username cần tìm, optional empty nếu không tìm thấy
     */
    Optional<UserPrincipal> findUserPrincipalByUsername(String username);

    List findGroupIdsByUsername(String username);

    int updateRefreshTokenByUsername(String refreshToken, String username);

    Optional findRefreshTokenByUsername(String username);
}

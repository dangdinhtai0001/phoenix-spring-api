package com.phoenix.api.base.repositories;

import com.phoenix.api.base.model.UserPrincipal;

import java.util.Optional;

public interface UserRepository {
    Optional<UserPrincipal> findUserPrincipalByUsername(String username);

    int updateRefreshTokenByUsername(String refreshToken, String username);

    Optional findUserProfileByUsername(String username);

    Optional findRefreshTokenByUsername(String username);
}

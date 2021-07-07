package com.phoenix.api.repositories.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.model.auth.UserPrincipal;
import com.phoenix.api.repositories.base.AbstractNativeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository(value = BeanIds.AUTH_REPOSITORY_IMP)
public class AuthRepositoryImp extends AbstractNativeRepository {
    public AuthRepositoryImp(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * @param username : usernamecuar người dùng
     * @return : Thông tin người dùng, bao gồm:
     * - id:
     * - username:
     * - password:
     * - hash algorithm
     * - password salt
     * - status:
     * - group:
     */
    public Optional<UserPrincipal> findUserByUsername(String username) {
        String sql = DatabaseConstant.FIND_USER_BY_USERNAME;
        Object[] record = executeNativeQuery(sql, username).get(0);

        try {
            Long id = Long.parseLong(String.valueOf(record[0]));
            String username_ = String.valueOf(record[1]);
            String password = String.valueOf(record[2]);
            String hashAlgorithm = String.valueOf(record[3]);
            String passwordSalt = String.valueOf(record[4]);
            int status = (Integer) record[5];
            String group = String.valueOf(record[6]);

            UserPrincipal userPrincipal = new UserPrincipal(id, username_, password, hashAlgorithm, passwordSalt,
                    0, status, group);

            return Optional.of(userPrincipal);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


}

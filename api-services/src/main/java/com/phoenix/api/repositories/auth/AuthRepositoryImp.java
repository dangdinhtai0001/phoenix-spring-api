package com.phoenix.api.repositories.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.model.auth.UserPrincipal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository(value = BeanIds.AUTH_REPOSITORY_IMP)
public class AuthRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<UserPrincipal> findUserByUsername(String username) {
        String sql = DatabaseConstant.SQL_FIND_USER_BY_USERNAME;
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1, username);

        List<Object[]> results = query.getResultList();

        Object[] record = results.get(0);

        try {
            Long id = Long.parseLong(String.valueOf(record[0]));
            String username_ = String.valueOf(record[1]);
            String password = String.valueOf(record[2]);
            String hashAlgorithm = String.valueOf(record[3]);
            String passwordSalt = String.valueOf(record[4]);
            int status = (Integer) record[5];
            String group = String.valueOf(record[6]);

            UserPrincipal userPrincipal = new UserPrincipal(id, username_, password, hashAlgorithm, passwordSalt, 0, status, group);

            return Optional.of(userPrincipal);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}

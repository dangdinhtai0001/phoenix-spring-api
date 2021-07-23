package com.phoenix.api.base.repositories;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.UserEntity;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.core.repository.AbstractBaseRepository;
import com.phoenix.common.structure.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository(BeanIds.USER_REPOSITORY_IMP)
public class UserRepositoryImp extends AbstractBaseRepository<UserEntity, Long> {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserRepositoryImp(EntityManager entityManager) {
        super(entityManager, UserEntity.class);
        this.entityManager = entityManager;
    }

    public Optional<UserPrincipal> findUserPrincipalByUsername(String username) {
        String sql = "select u.id, u.username, u.password, u.hash_algorithm,  u.password_salt, fus.name status from fw_user " +
                "u left join fw_user_status fus on u.status = fus.id where u.username = ?";

        List<Object[]> objects = executeNativeQuery(sql, username);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("id", Long.class));
        params.add(new Pair<>("username", String.class));
        params.add(new Pair<>("password", String.class));
        params.add(new Pair<>("hashAlgorithm", String.class));
        params.add(new Pair<>("passwordSalt", String.class));
        params.add(new Pair<>("status", String.class));

        UserPrincipal userPrincipal;
        try {
            userPrincipal = (UserPrincipal) parseResult(objects.get(0), params, UserPrincipal.class);
        } catch (IndexOutOfBoundsException e) {
            userPrincipal = null;
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            userPrincipal = null;
        }
        return Optional.ofNullable(userPrincipal);
    }

    @Modifying
    @Transactional
    public int updateRefreshTokenByUsername(String refreshToken, String username) {
        String sql = "update fw_user set refresh_token = ? where username = ?";

        return updateNativeQuery(sql, refreshToken, username);
    }
}

package com.phoenix.api.base.repositories.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.UserEntity;
import com.phoenix.api.base.model.UserPrincipal;
import com.phoenix.api.base.repositories.UserRepository;
import com.phoenix.api.business.model.User;
import com.phoenix.api.core.repository.AbstractBaseRepository;
import com.phoenix.common.structure.Pair;
import com.phoenix.common.util.DateUtil;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository(BeanIds.BASE_USER_REPOSITORY_IMP)
public class UserRepositoryImp extends AbstractBaseRepository<UserEntity, Long> implements UserRepository {

    public UserRepositoryImp(EntityManager entityManager) {
        super(entityManager, UserEntity.class);
    }

    /**
     * @param username Tài khoản cần tìm kiếm
     * @return Optional của UserPrincipal có username cần tìm, optional empty nếu không tìm thấy
     */
    public Optional<UserPrincipal> findUserPrincipalByUsername(String username) {
        String sql = "select u.id, u.username, u.password, u.hash_algorithm,  u.password_salt, fus.name status from fw_user " +
                "u left join fw_user_status fus on u.status_id = fus.id where u.username = ?";

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

    public Optional findUserProfileByUsername(String username) {
        String sql = "select fu.id, p.name, p.date_of_birth, p.gender, p.phone_number, p.avatar, fu.password\n" +
                "from fw_user fu left join profile p on fu.id = p.USER_ID where fu.username = ?";

        List<Object[]> objects = executeNativeQuery(sql, username);
        User user;

        if (objects.isEmpty()) {
            user = null;
        } else {
            Object[] record = objects.get(0);

            Long id = Long.parseLong(String.valueOf(record[0]));
            String name = String.valueOf(record[1]);
            Date dateOfBirth = DateUtil.convertString2Date(String.valueOf(record[2]), "yyyy-MM-dd");
            String gender = String.valueOf(record[3]);
            String phoneNumber = String.valueOf(record[4]);
            String avatar = String.valueOf(record[5]);
            String password = "";

            user = new User(id, name, dateOfBirth, gender, phoneNumber, avatar, username, password);
        }

        return Optional.ofNullable(user);
    }

    public Optional findRefreshTokenByUsername(String username) {
        String sql = "select refresh_token from fw_user where username = ?";
        List queryResult = executeNativeQuery(sql, username);

        if (queryResult.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(queryResult.get(0));
    }
}

package com.phoenix.api.services.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.model.auth.DefaultUserDetails;
import com.phoenix.api.model.auth.UserPrincipal;
import com.phoenix.api.repositories.auth.AuthRepositoryImp;
import com.phoenix.api.util.CommonUtil;
import com.phoenix.others.BitUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(BeanIds.DEFAULT_USER_DETAIL_SERVICES)
@Log4j2
public class DefaultUserDetailService implements UserDetailsService {

    private final AuthRepositoryImp authRepositoryImp;

    private final List<UserStatusEntity> allUserStatus;
    private final List<PermissionEntity> allPermissions;

    public DefaultUserDetailService(
            @Qualifier(BeanIds.AUTH_REPOSITORY_IMP) AuthRepositoryImp authRepositoryImp,
            @Qualifier(BeanIds.ALL_USER_STATUS) List<UserStatusEntity> userStatusEntities,
            @Qualifier(BeanIds.ALL_PERMISSIONS) List<PermissionEntity> allPermissions) {
        this.authRepositoryImp = authRepositoryImp;
        this.allUserStatus = userStatusEntities;
        this.allPermissions = allPermissions;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPrincipal> optional = authRepositoryImp.findUserByUsername(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("Couldn't find a matching user username in the database for: " + username);
        }

        UserPrincipal userPrincipal = optional.get();

        userPrincipal.setListStatus(getListStatus(userPrincipal.getStatus()));
        try {
            userPrincipal.setPermissions(CommonUtil.getSetPermissions(username, authRepositoryImp, allPermissions));
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage(), e);
            userPrincipal.setPermissions(new HashSet<>());
        }

        return new DefaultUserDetails(userPrincipal);
    }

    /**
     * @param status : mask trạng thái của user
     * @return : Danh sách các trạng thái của user (chi tiết trong bảng fw_user_status)
     */
    private List<String> getListStatus(int status) {
        List<String> listStatus = new LinkedList<>();
        int[] positions = BitUtil.getAllBitOnePosition(status, allUserStatus.size());

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] > 0) {
                listStatus.add(allUserStatus.get(i).getName());
            }
        }
        return listStatus;
    }
}

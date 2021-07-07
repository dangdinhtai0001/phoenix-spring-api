package com.phoenix.api.services.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.model.auth.DefaultUserDetails;
import com.phoenix.api.model.auth.UserPrincipal;
import com.phoenix.api.repositories.auth.AuthRepositoryImp;
import com.phoenix.others.BitUtil;
import com.phoenix.structure.Pair;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            userPrincipal.setPermissions(getListPermissions(username));
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage(), e);
            userPrincipal.setPermissions(new LinkedList<>());
        }

        return new DefaultUserDetails(userPrincipal);
    }

    /**
     * @param status : mask trạng thái của user
     * @return : Danh sách các trạng thái của user (chi tiết trong bảng fw_user_status)
     */
    private List<String> getListStatus(int status) {
        List<String> listStatus;
        int[] positions = BitUtil.getAllBitOnePosition(status, allUserStatus.size());

        listStatus = Arrays
                .stream(positions)
                .filter(position -> position > -1)
                .mapToObj(position -> allUserStatus.get(position).getName())
                .collect(Collectors.toCollection(LinkedList::new));
        return listStatus;
    }

    /**
     * @param username : Tên username
     * @return : Danh sách các quyền của user (dạng: resource__permission)
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private List<String> getListPermissions(String username) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Object[]> results = authRepositoryImp.executeNativeQuery(DatabaseConstant.FIND_PERMISSIONS_BY_USERNAME, username, username);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("mFirst", String.class));
        params.add(new Pair<>("mSecond", String.class));
        List<Pair> list = authRepositoryImp.parseResult(results, params, Pair.class);

        List<String> permissions = new LinkedList<>();

        for (Pair pair : list) {
            permissions = generatePermissions(permissions, Integer.parseInt(String.valueOf(pair.second())), String.valueOf(pair.first()));
        }

        return permissions;
    }

    private List<String> generatePermissions(List<String> permissions, int mask, String resource) {
        int[] positions = BitUtil.getAllBitOnePosition(mask, allPermissions.size());

        permissions.addAll(Arrays
                .stream(positions)
                .filter(position -> position > -1)
                .mapToObj(position -> resource + "__" + allPermissions.get(position).getName())
                .collect(Collectors.toCollection(LinkedList::new)));

        return permissions;
    }
}

package com.phoenix.api.util;

import com.phoenix.api.constant.ApplicationConstant;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.repositories.base.NativeRepository;
import com.phoenix.others.BitUtil;
import com.phoenix.structure.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class CommonUtil {

    public static Set<String> getSetPermissions(String username, NativeRepository nativeRepository, List<PermissionEntity> allPermissions)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Object[]> results = nativeRepository.executeNativeQuery(DatabaseConstant.FIND_PERMISSIONS_BY_USERNAME, username, username);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("mFirst", String.class));
        params.add(new Pair<>("mSecond", String.class));
        List<Pair> list = nativeRepository.parseResult(results, params, Pair.class);

        Set<String> permissions = new HashSet<>();

        for (Pair pair : list) {
            permissions = generatePermissions(permissions, Integer.parseInt(String.valueOf(pair.second())), String.valueOf(pair.first()), allPermissions);
        }

        return permissions;
    }

    public static Set<String> generatePermissions(Set<String> permissions, int mask, String resource, List<PermissionEntity> allPermissions) {
        int[] positions = BitUtil.getAllBitOnePosition(mask, allPermissions.size());

        for (int i = 0; i < positions.length; i++) {
            if(positions[i] > 0){
                permissions.add(resource + ApplicationConstant.PERMISSION_SPERATE + allPermissions.get(i).getName());
            }
        }

        return permissions;
    }


}

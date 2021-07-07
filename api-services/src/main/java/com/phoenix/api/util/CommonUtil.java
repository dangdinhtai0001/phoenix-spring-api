package com.phoenix.api.util;

import com.phoenix.api.constant.ApplicationConstant;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.repositories.base.NativeRepository;
import com.phoenix.others.BitUtil;
import com.phoenix.structure.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtil {

    public static List<String> getListPermissions(String username, NativeRepository nativeRepository, List<PermissionEntity> allPermissions)
            throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        List<Object[]> results = nativeRepository.executeNativeQuery(DatabaseConstant.FIND_PERMISSIONS_BY_USERNAME, username, username);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("mFirst", String.class));
        params.add(new Pair<>("mSecond", String.class));
        List<Pair> list = nativeRepository.parseResult(results, params, Pair.class);

        List<String> permissions = new LinkedList<>();

        for (Pair pair : list) {
            permissions = generatePermissions(permissions, Integer.parseInt(String.valueOf(pair.second())), String.valueOf(pair.first()), allPermissions);
        }

        return permissions;
    }

    private static List<String> generatePermissions(List<String> permissions, int mask, String resource, List<PermissionEntity> allPermissions) {
        int[] positions = BitUtil.getAllBitOnePosition(mask, allPermissions.size());

        permissions.addAll(Arrays
                .stream(positions)
                .filter(position -> position > -1)
                .mapToObj(position -> resource + ApplicationConstant.PERMISSION_SPERATE + allPermissions.get(position).getName())
                .collect(Collectors.toCollection(LinkedList::new)));

        return permissions;
    }
}

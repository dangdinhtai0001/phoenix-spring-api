/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.api.repositories.auth;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.util.BitUtil;
import com.phoenix.structure.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class TestAuthRepositoryImp {

    @Autowired
    private AuthRepositoryImp authRepositoryImp;

    @Autowired
    @Qualifier(BeanIds.ALL_PERMISSIONS)
    private List<PermissionEntity> allPermissions;

    @Test
    public void testLoadAllPermissions() throws Exception {
        String sql = "select DISTINCT fr.name  resource\n" +
                "              , fsr.mask mask\n" +
                "#               , fs.id    sid\n" +
                "#               , fs.name  name\n" +
                "from fw_sid fs\n" +
                "         left join fw_sid_resource fsr on fs.id = fsr.sid\n" +
                "         left join fw_resource fr on fsr.resource_id = fr.id\n" +
                "where (fs.name = ?\n" +
                "    or fs.name in (select fg.name\n" +
                "                   from fw_user fu\n" +
                "                            left join fw_user_group fug on fu.id = fug.user_id\n" +
                "                            left join fw_group fg on fug.group_id = fg.id\n" +
                "                   where fu.username = ?))\n" +
                "  and fr.name IS NOT NULL";
        String username = "admin_test";
        List<Object[]> results = authRepositoryImp.executeNativeQuery(sql, username, username);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("mFirst", String.class));
        params.add(new Pair<>("mSecond", String.class));
        List<Pair> list = authRepositoryImp.parseResult(results, params, Pair.class);

        List<String> permissions = new LinkedList<>();

        for (Pair pair : list) {
//            System.out.println(pair.first());
            permissions = generatePermissions(permissions, Integer.parseInt(String.valueOf(pair.second())), String.valueOf(pair.first()));
        }

        System.out.println(permissions);

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

    @Test
    public void testUnMaskPermissions() {
        List<String> listPermission;
        int[] positions = BitUtil.getAllBitOnePosition(31, allPermissions.size());

        for (int i = 0; i < positions.length; i++) {
            System.out.println(positions[i]);
        }

        listPermission = Arrays
                .stream(positions)
                .filter(position -> position > -1)
                .mapToObj(position -> allPermissions.get(position).getName())
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(listPermission);
    }
}

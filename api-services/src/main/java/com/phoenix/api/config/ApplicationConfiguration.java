package com.phoenix.api.config;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.constant.DatabaseConstant;
import com.phoenix.api.entities.auth.PermissionEntity;
import com.phoenix.api.entities.auth.UserStatusEntity;
import com.phoenix.api.entities.common.ExceptionEntity;
import com.phoenix.api.repositories.auth.PermissionRepositoryImp;
import com.phoenix.api.repositories.auth.UserStatusRepository;
import com.phoenix.api.repositories.common.ExceptionRepositoryImp;
import com.phoenix.api.util.CommonUtil;
import com.phoenix.auth.JwtProvider;
import com.phoenix.auth.imp.DefaultJwtProvider;
import com.phoenix.text.HashingText;
import com.phoenix.util.UUIDFactory;
import com.phoenix.util.imp.ConcurrentUUIDFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Nơi nạp các bean cần thiết cho ứng dụng
 */
@Configuration(value = "ApplicationConfiguration")
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    private final UserStatusRepository userStatusRepository;
    private final ExceptionRepositoryImp exceptionRepositoryImp;
    private final PermissionRepositoryImp permissionRepositoryImp;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.USER_STATUS_REPOSITORY) UserStatusRepository userStatusRepository,
            @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP) ExceptionRepositoryImp exceptionRepositoryImp,
            @Qualifier(BeanIds.PERMISSION_REPOSITORY_IMP) PermissionRepositoryImp permissionRepositoryImp) {
        this.userStatusRepository = userStatusRepository;
        this.exceptionRepositoryImp = exceptionRepositoryImp;
        this.permissionRepositoryImp = permissionRepositoryImp;
    }

    /**
     * @return : List các status của user từ database
     */
    @Bean(value = BeanIds.ALL_USER_STATUS)
    public List<UserStatusEntity> getAllUserStatus() {
        return userStatusRepository.findAll();
    }

    /**
     * @return : List thông tin các Exception được định nghĩa trong database trong database
     */
    @Bean(value = BeanIds.ALL_EXCEPTION)
    public List<ExceptionEntity> getAllException() {
        return (List<ExceptionEntity>) exceptionRepositoryImp.findAll();
    }

    /**
     * @return : Nạp khóa bí mật của ứng dụng.
     */
    @Bean(value = BeanIds.JWT_SECRET_KEY)
    public String getSecretKey() {
        return HashingText.hashingSha256(this.secret);
    }

    /**
     * @param secretKey : khóa bí mật của ứng dụng
     * @return : Đối tuwojng dùng để generate + validate token (dùng trong quá trình xác thực người dùng)
     */
    @Bean(value = BeanIds.JWT_PROVIDER)
    @DependsOn(BeanIds.JWT_SECRET_KEY)
    public JwtProvider getJwtProvider(@Qualifier(BeanIds.JWT_SECRET_KEY) String secretKey) {
        return new DefaultJwtProvider(secretKey, Long.parseLong(jwtExpired));
    }

    /**
     * @return : Đối tượng sinh mã UUID duy nhất.
     */
    @Bean(value = BeanIds.UUID_Factory)
    public UUIDFactory getUUIDFactory() {
        return new ConcurrentUUIDFactory();
    }

    /**
     * @return Danh sách tất cả quyền trong ứng dụng
     */
    @Bean(value = BeanIds.ALL_PERMISSIONS)
    public List<PermissionEntity> getAllPermissions() {
        return (List<PermissionEntity>) permissionRepositoryImp.findAll();
    }

    @Bean(value = BeanIds.ALL_RESOURCE_PERMISSIONS_REQUIRED)
    public LinkedHashMap<String, String> getAllResourcePermissionRequirement() {
        List<Object[]> result = permissionRepositoryImp.executeNativeQuery(DatabaseConstant.FW_ALL_RESOURCE_PERMISSIONS_REQUIRED);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        List<String> permissions = new LinkedList<>();
        String strPermissions = "";
        for (Object[] record : result) {
            permissions = CommonUtil.generatePermissions(permissions,
                    Integer.parseInt(String.valueOf(record[1])),
                    String.valueOf(record[0]), getAllPermissions());
            strPermissions = generateStringFromList(permissions, ", ");

            map.put(String.valueOf(record[0]), strPermissions);

        }
        return map;
    }

    //    ==============================================================================================================
    //
    //    ==============================================================================================================

    private String generateStringFromList(List<String> list, String delimiter) {
        return list.stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.joining(delimiter, "", ""));
    }
}

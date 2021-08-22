package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.imp.ExceptionRepositoryImp;
import com.phoenix.api.base.service.AuthorizationService;
import com.phoenix.common.auth.JwtProvider;
import com.phoenix.common.auth.imp.DefaultJwtProvider;
import com.phoenix.common.text.HashingText;
import com.phoenix.common.util.UUIDFactory;
import com.phoenix.common.util.imp.ConcurrentUUIDFactory;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import lombok.extern.log4j.Log4j2;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Configuration(value = "ApplicationConfiguration")
@Log4j2
public class ApplicationConfiguration {
    @Value("${application.jwt.secret}")
    private String secret;

    @Value("${application.jwt.expired}")
    private String jwtExpired;

    @Value("${application.authorization.model-path}")
    private String authorizationModelPath;


    private final DataSource dataSource;

    private final ExceptionRepositoryImp exceptionRepositoryImp;
    private final AuthorizationService authorizationService;

    public ApplicationConfiguration(
            @Qualifier(BeanIds.EXCEPTION_REPOSITORY_IMP) ExceptionRepositoryImp exceptionRepositoryImp,
            @Qualifier(BeanIds.AUTHORIZATION_SERVICES) AuthorizationService authorizationService,
            DataSource dataSource) {
        this.exceptionRepositoryImp = exceptionRepositoryImp;
        this.authorizationService = authorizationService;
        this.dataSource = dataSource;
    }

    @Bean(BeanIds.SQL_QUERY_FACTORY)
    public SQLQueryFactory createSqlQueryFactory() {
        SQLTemplates templates = MySQLTemplates.builder()
                .printSchema() // to include the schema in the output
                .build();

        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);

        Provider<Connection> provider = new SpringConnectionProvider(dataSource);
        log.info("Creating sql query factory");
        return new SQLQueryFactory(configuration, provider);
    }

    /**
     * @return Enforcer của casbin dùng để xác định quyền của subject đối với object
     */
    @Bean(BeanIds.AUTHORIZATION_ENFORCE)
    public Enforcer createAuthorizationEnforcer() {
        Model model = authorizationService.loadModelFromPath(authorizationModelPath);
        Enforcer enforcer = new Enforcer(model);
        enforcer.setAutoNotifyDispatcher(false);
        enforcer.enableAutoSave(false);
        enforcer.enableLog(false);
        log.info("Creating authorization enforcer");
        return enforcer;
    }

    /**
     * @return : List thông tin các Exception được định nghĩa trong database
     */
    @Bean(value = BeanIds.ALL_EXCEPTION)
    public List<ExceptionEntity> getAllException() {
        log.info("get all exception from database");
        return exceptionRepositoryImp.findAll();
    }

    /**
     * @return : Nạp khóa bí mật của ứng dụng.
     */
    @Bean(value = BeanIds.JWT_SECRET_KEY)
    public String getSecretKey() {
        log.info("create secret key");
        return HashingText.hashingSha256(this.secret);
    }

    /**
     * @param secretKey : khóa bí mật của ứng dụng
     * @return : Đối tượng dùng để generate + validate token (dùng trong quá trình xác thực người dùng)
     */
    @Bean(value = BeanIds.JWT_PROVIDER)
    @DependsOn(BeanIds.JWT_SECRET_KEY)
    public JwtProvider getJwtProvider(@Qualifier(BeanIds.JWT_SECRET_KEY) String secretKey) {
        log.info("create jwt provider");
        return new DefaultJwtProvider(secretKey, Long.parseLong(jwtExpired));
    }

    /**
     * @return : Đối tượng sinh mã UUID duy nhất.
     */
    @Bean(value = BeanIds.UUID_FACTORY)
    public UUIDFactory getUUIDFactory() {
        log.info("create UUID factory");
        return new ConcurrentUUIDFactory();
    }
}

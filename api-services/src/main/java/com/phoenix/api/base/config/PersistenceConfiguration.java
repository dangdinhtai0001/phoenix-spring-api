package com.phoenix.api.base.config;

import com.phoenix.api.base.constant.BeanIds;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.sql.Connection;

@Configuration(value = "PersistenceConfiguration")
@EnableJpaAuditing
@EnableTransactionManagement
@Log4j2
public class PersistenceConfiguration {

    private final DataSource dataSource;

    public PersistenceConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean(BeanIds.SQL_QUERY_FACTORY)
    public SQLQueryFactory createSqlQueryFactory() {
        //https://querydsl.com/static/querydsl/latest/reference/html/ch02s03.html

        SQLTemplates templates = MySQLTemplates.builder()
                .printSchema() // to include the schema in the output
                .build();

        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());

        Provider<Connection> provider = new SpringConnectionProvider(dataSource);
        log.info("Creating sql query factory");
        return new SQLQueryFactory(configuration, provider);
    }
}

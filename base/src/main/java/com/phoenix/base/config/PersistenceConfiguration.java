package com.phoenix.base.config;

import com.phoenix.base.constant.BeanIds;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
                .quote()       // to quote names
                .newLineToSingleSpace() // to replace new lines with single space in the output
                .build();      // to get the customized SQLTemplates instance

        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());

        Provider<Connection> provider = new SpringConnectionProvider(dataSource);
        log.info("Creating sql query factory");
        return new SQLQueryFactory(configuration, provider);
    }
}

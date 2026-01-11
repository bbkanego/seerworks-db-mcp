package com.seerworks.db.mcp.empdb.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// https://www.baeldung.com/spring-boot-configure-multiple-datasources
@Configuration
public class MultipleDataSourceConfiguration {

    @Bean("seerWorksDSProperties")
    @ConfigurationProperties("spring.datasource.seerworks")
    public DataSourceProperties seerWorksDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary // this is needed since if we do not do this, then EntityManagerFactoryBuilder will not be injected
    @Bean("seerWorksDataSource")
    public DataSource seerWorksDataSource(DataSourceProperties seerWorksDSProperties) {
        return seerWorksDSProperties.initializeDataSourceBuilder().build();
    }

    @Bean("seerWorksJdbcTemplate")
    public JdbcTemplate seerWorksJdbcTemplate(@Qualifier("seerWorksDataSource") DataSource seerWorksDataSource) {
        return new JdbcTemplate(seerWorksDataSource);
    }
}

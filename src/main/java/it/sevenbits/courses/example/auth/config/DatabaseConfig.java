package it.sevenbits.courses.example.auth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @FlywayDataSource
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * The method creates instance of jdbcTemplate
     * @param dataSource data source
     * @return instance of jdbcTemplate
     */
    @Bean
    @Qualifier("JdbcTemplate")
    public JdbcTemplate getJdbcTemplate(final @Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

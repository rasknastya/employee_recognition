package it.sevenbits.courses.example.auth.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * Database migrations config
 */
@Configuration
public class DatabaseMigrationsConfig {
    private final DataSource dataSource;


    /**
     * Instantiates a new Database migrations config
     *
     * @param dataSource the data source
     */
    public DatabaseMigrationsConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Management flyway
     *
     * @return the flyway
     */
    @Bean
    public Flyway managementFlyway() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(this.dataSource)
                .table("flyway_schema")
                .baselineOnMigrate(true)
                .locations("db/migrations")
                .load();
        try {
            flyway.migrate();
        } catch (FlywayException e) {
            flyway.repair();
            flyway.migrate();
        }
        return flyway;
    }
}

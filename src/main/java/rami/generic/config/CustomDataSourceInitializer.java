package rami.generic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Configuration class to initialize the database conditionally based on data presence.
 */
@Configuration
public class CustomDataSourceInitializer {

    /**
     * Path to data-mysql.sql.
     */
    @Value("classpath:data.sql")
    private Resource dataScript;

    /**
     * Creates a CommandLineRunner bean to check if the database contains data.
     * If the database is empty, it inserts records manually.
     *
     * @param dataSource the file with inserts for data.
     * @param jdbcTemplate the JdbcTemplate used to execute SQL queries
     * @return a CommandLineRunner bean that performs the data check
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM dummy", Integer.class);
        if (count == 0) {
            initializer.setDatabasePopulator(new ResourceDatabasePopulator(dataScript));
        }
        return initializer;
    }
}

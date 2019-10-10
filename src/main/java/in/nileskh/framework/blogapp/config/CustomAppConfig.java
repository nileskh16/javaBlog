package in.nileskh.framework.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
@EnableAutoConfiguration
public class CustomAppConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties getDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource getDataSource() {
        return getDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(DriverManagerDataSource.class)
                .build();
    }
}

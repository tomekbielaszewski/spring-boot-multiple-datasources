package pl.grizwold.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "fooEntityManagerFactory",
        transactionManagerRef = "fooTransactionManager",
        basePackages = {
                "pl.grizwold.model.foo"})
public class FooDatasourceConfig {
    @Bean(name = "fooDataSource")
    @ConfigurationProperties(prefix = "foo.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "fooEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("fooDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("pl.grizwold.model.foo")
                .persistenceUnit("foo")
                .build();
    }

    @Bean(name = "fooTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("fooEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "fooJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("fooDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

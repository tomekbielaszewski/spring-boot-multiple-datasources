package pl.grizwold.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tarEntityManagerFactory",
        transactionManagerRef = "tarTransactionManager",
        basePackages = {
                "pl.grizwold.model.tar"})
public class TarDatasourceConfig {
    @Bean(name = "tarDataSource")
    @ConfigurationProperties(prefix = "tar.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tarEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tarDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("pl.grizwold.model.tar")
                .persistenceUnit("tar")
                .build();
    }

    @Bean(name = "tarTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("tarEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

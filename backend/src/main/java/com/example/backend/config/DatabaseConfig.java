package com.example.backend.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    // Database URLs
    @Value("${spring.datasource.user.url}")
    private String userDbUrl;

    @Value("${spring.datasource.authentication.url}")
    private String authenticationDbUrl;

    @Value("${spring.datasource.auction.url}")
    private String auctionDbUrl;

    @Value("${spring.datasource.payment.url}")
    private String paymentDbUrl;

    // DataSource Beans
    private DataSource createDataSource(String url) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public DataSource userDataSource() {
        return createDataSource(userDbUrl);
    }

    @Bean
    public DataSource authenticationDataSource() {
        return createDataSource(authenticationDbUrl);
    }

    @Bean
    public DataSource auctionDataSource() {
        return createDataSource(auctionDbUrl);
    }

    @Bean
    public DataSource paymentDataSource() {
        return createDataSource(paymentDbUrl);
    }

    // EntityManagerFactory and TransactionManager Configurations
    private LocalContainerEntityManagerFactoryBean createEntityManagerFactory(
            DataSource dataSource, String packagesToScan, String persistenceUnitName) {

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(packagesToScan);
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaPropertyMap(properties);
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
        return createEntityManagerFactory(
                userDataSource(), "com.example.backend.model.user", "userPersistenceUnit");
    }

    @Bean(name = "authenticationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authenticationEntityManagerFactory() {
        return createEntityManagerFactory(
                authenticationDataSource(), "com.example.backend.model.authentication", "authenticationPersistenceUnit");
    }

    @Bean(name = "auctionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean auctionEntityManagerFactory() {
        return createEntityManagerFactory(
                auctionDataSource(), "com.example.backend.model.auction", "auctionPersistenceUnit");
    }

    @Bean(name = "paymentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean paymentEntityManagerFactory() {
        return createEntityManagerFactory(
                paymentDataSource(), "com.example.backend.model.payment", "paymentPersistenceUnit");
    }

    // Transaction Managers
    @Primary
    @Bean(name = "userTransactionManager")
    public JpaTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }

    @Bean(name = "authenticationTransactionManager")
    public JpaTransactionManager authenticationTransactionManager(
            @Qualifier("authenticationEntityManagerFactory") EntityManagerFactory authenticationEntityManagerFactory) {
        return new JpaTransactionManager(authenticationEntityManagerFactory);
    }

    @Bean(name = "auctionTransactionManager")
    public JpaTransactionManager auctionTransactionManager(
            @Qualifier("auctionEntityManagerFactory") EntityManagerFactory auctionEntityManagerFactory) {
        return new JpaTransactionManager(auctionEntityManagerFactory);
    }

    @Bean(name = "paymentTransactionManager")
    public JpaTransactionManager paymentTransactionManager(
            @Qualifier("paymentEntityManagerFactory") EntityManagerFactory paymentEntityManagerFactory) {
        return new JpaTransactionManager(paymentEntityManagerFactory);
    }
}

// Separate repository configurations for each database to bind them to specific EntityManagerFactory and TransactionManager
@Configuration
@EntityScan(basePackages = "com.example.backend.model.user")
@EnableJpaRepositories(
        basePackages = "com.example.backend.repository.user",
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
class UserDbConfig {}

@Configuration
@EntityScan(basePackages = "com.example.backend.model.authentication")
@EnableJpaRepositories(
        basePackages = "com.example.backend.repository.authentication",
        entityManagerFactoryRef = "authenticationEntityManagerFactory",
        transactionManagerRef = "authenticationTransactionManager"
)
class AuthenticationDbConfig {}

@Configuration
@EntityScan(basePackages = "com.example.backend.model.auction")
@EnableJpaRepositories(
        basePackages = "com.example.backend.repository.auction",
        entityManagerFactoryRef = "auctionEntityManagerFactory",
        transactionManagerRef = "auctionTransactionManager"
)
class AuctionDbConfig {}

@Configuration
@EntityScan(basePackages = "com.example.backend.model.payment")
@EnableJpaRepositories(
        basePackages = "com.example.backend.repository.payment",
        entityManagerFactoryRef = "paymentEntityManagerFactory",
        transactionManagerRef = "paymentTransactionManager"
)
class PaymentDbConfig {}

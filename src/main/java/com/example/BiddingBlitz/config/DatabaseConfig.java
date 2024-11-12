package com.example.BiddingBlitz.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
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
@EnableJpaRepositories(basePackages = {
        "com.example.BiddingBlitz.repository.user",
        "com.example.BiddingBlitz.repository.authentication",
        "com.example.BiddingBlitz.repository.payment",
        "com.example.BiddingBlitz.repository.auction"
})
@EntityScan(basePackages = {
        "com.example.BiddingBlitz.model.user",
        "com.example.BiddingBlitz.model.authentication",
        "com.example.BiddingBlitz.model.payment",
        "com.example.BiddingBlitz.model.auction"
})
public class DatabaseConfig {

    @Value("${spring.user.url}")
    private String userDbUrl;

    @Value("${spring.authentication.url}")
    private String authenticationDbUrl;

    @Value("${spring.auction.url}")
    private String auctionDbUrl;

    @Value("${spring.payment.url}")
    private String paymentDbUrl;

    // DataSource Beans
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

    private DataSource createDataSource(String url) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl(url);
        return dataSource;
    }

    // EntityManagerFactory Beans
    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
        return createEntityManagerFactory(
                userDataSource(),
                "com.example.BiddingBlitz.model.user",
                "userPersistenceUnit"
        );
    }

    @Bean(name = "authenticationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authenticationEntityManagerFactory() {
        return createEntityManagerFactory(
                authenticationDataSource(),
                "com.example.BiddingBlitz.model.authentication, com.example.BiddingBlitz.model.user",
                "authenticationPersistenceUnit"
        );
    }

    @Bean(name = "auctionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean auctionEntityManagerFactory() {
        return createEntityManagerFactory(
                auctionDataSource(),
                "com.example.BiddingBlitz.model.auction, com.example.BiddingBlitz.model.user",
                "auctionPersistenceUnit"
        );
    }

    @Bean(name = "paymentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean paymentEntityManagerFactory() {
        return createEntityManagerFactory(paymentDataSource(),
                "com.example.BiddingBlitz.model.payment, com.example.BiddingBlitz.model.auction",
                "paymentPersistenceUnit"
        );
    }

    // Default EntityManagerFactory
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return createEntityManagerFactory(userDataSource(),
                "com.example.BiddingBlitz.model",
                "defaultPersistenceUnit"
        );
    }

    private LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource, String packageToScan, String persistenceUnitName) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(packageToScan);
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaPropertyMap(properties);
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return factoryBean;
    }

    // Transaction Manager Beans
    @Primary
    @Bean(name = "userTransactionManager")
    public JpaTransactionManager userTransactionManager(EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }

    @Bean(name = "authenticationTransactionManager")
    public JpaTransactionManager authenticationTransactionManager(EntityManagerFactory authenticationEntityManagerFactory) {
        return new JpaTransactionManager(authenticationEntityManagerFactory);
    }

    @Bean(name = "auctionTransactionManager")
    public JpaTransactionManager auctionTransactionManager(EntityManagerFactory auctionEntityManagerFactory) {
        return new JpaTransactionManager(auctionEntityManagerFactory);
    }

    @Bean(name = "paymentTransactionManager")
    public JpaTransactionManager paymentTransactionManager(EntityManagerFactory paymentEntityManagerFactory) {
        return new JpaTransactionManager(paymentEntityManagerFactory);
    }
}
package com.example.BiddingBlitz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory() {
        return createEntityManagerFactory(userDataSource(), "com.example.BiddingBlitz.model.user", "userPersistenceUnit");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean authenticationEntityManagerFactory() {
        return createEntityManagerFactory(authenticationDataSource(), "com.example.BiddingBlitz.model.authentication", "authenticationPersistenceUnit");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean auctionEntityManagerFactory() {
        return createEntityManagerFactory(auctionDataSource(), "com.example.BiddingBlitz.model.auction", "auctionPersistenceUnit");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean paymentEntityManagerFactory() {
        return createEntityManagerFactory(paymentDataSource(), "com.example.BiddingBlitz.model.payment", "paymentPersistenceUnit");
    }

    private LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource, String packageToScan, String persistenceUnitName) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect"); // Specify SQLite dialect
        properties.put("hibernate.hbm2ddl.auto", "update"); // Automatically update schema (set it to 'validate' for production)
        properties.put("hibernate.show_sql", "true");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(packageToScan);
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    // Transaction Manager Beans
    @Primary
    @Bean
    public JpaTransactionManager userTransactionManager(EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }

    @Bean
    public JpaTransactionManager authenticationTransactionManager(EntityManagerFactory authenticationEntityManagerFactory) {
        return new JpaTransactionManager(authenticationEntityManagerFactory);
    }

    @Bean
    public JpaTransactionManager auctionTransactionManager(EntityManagerFactory auctionEntityManagerFactory) {
        return new JpaTransactionManager(auctionEntityManagerFactory);
    }

    @Bean
    public JpaTransactionManager paymentTransactionManager(EntityManagerFactory paymentEntityManagerFactory) {
        return new JpaTransactionManager(paymentEntityManagerFactory);
    }
}

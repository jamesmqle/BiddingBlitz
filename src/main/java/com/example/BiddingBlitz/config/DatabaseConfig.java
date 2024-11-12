package com.example.BiddingBlitz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

//    // DataSource 1 (for Database 1)
//    @Bean(name = "dataSource1")
//    @Primary
//    public DataSource dataSource1() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:/path/to/your/first/database.db");  // Set path for SQLite DB
//        return dataSource;
//    }
//
//    // DataSource 2 (for Database 2)
//    @Bean(name = "dataSource2")
//    public DataSource dataSource2() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.sqlite.JDBC");
//        dataSource.setUrl("jdbc:sqlite:/path/to/your/second/database.db");  // Set path for second SQLite DB
//        return dataSource;
//    }
//
//    // JdbcTemplate for Database 1
//    @Bean(name = "jdbcTemplate1")
//    @Primary
//    public JdbcTemplate jdbcTemplate1(@Qualifier("dataSource1") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    // JdbcTemplate for Database 2
//    @Bean(name = "jdbcTemplate2")
//    public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    // TransactionManager for Database 1
//    @Bean(name = "transactionManager1")
//    @Primary
//    public DataSourceTransactionManager transactionManager1(@Qualifier("dataSource1") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    // TransactionManager for Database 2
//    @Bean(name = "transactionManager2")
//    public DataSourceTransactionManager transactionManager2(@Qualifier("dataSource2") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}

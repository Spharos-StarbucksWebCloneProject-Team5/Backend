//package com.example.Starbucks.replica;
//
//import com.zaxxer.hikari.HikariDataSource;
//import javax.sql.DataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Slf4j
//@Configuration
//public class DataSourceConfig {
//
//    private final String MASTER_DATA_SOURCE = "masterDataSource";
//    private final String SLAVE_DATA_SOURCE = "slaveDataSource";
//
//    @Primary
//    @Bean(MASTER_DATA_SOURCE)
//    @ConfigurationProperties(prefix = "spring.master.datasource")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean(SLAVE_DATA_SOURCE)
//    @ConfigurationProperties(prefix = "spring.slave.datasource")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .build();
//    }
//}
//package com.example.Starbucks.replica;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
//    @Override
//    protected Object determineCurrentLookupKey() {
//        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
//        log.info("current dataSourceType is ReadOnly ? : {}", isReadOnly);
//        return isReadOnly ? "read" : "write";
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.write.datasource")
//    public DataSource writeDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.read.datasource")
//    public DataSource readDataSource() {
//        return DataSourceBuilder
//                .create()
//                .type(HikariDataSource.class)
//                .build();
//    }
//
//    @Bean
//    public DataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
//                                        @Qualifier("readDataSource") DataSource readDataSource) {
//        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
//
//        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
//        dataSourceMap.put("write", writeDataSource);
//        dataSourceMap.put("read", readDataSource);
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.setDefaultTargetDataSource(writeDataSource);
//
//        return routingDataSource;
//    }
//
//    @Bean
//    public DataSource routingLazyDataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
//        return new LazyConnectionDataSourceProxy(routingDataSource);
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("routingLazyDataSource") DataSource dataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }
//}

//package com.example.Starbucks.replica;
//
//import com.example.Starbucks.enums.DataSourceType;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//public class RoutingDataSource extends AbstractRoutingDataSource {
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
//                ? DataSourceType.SLAVE : DataSourceType.MASTER;
//    }
//}
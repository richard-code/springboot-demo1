package com.study.config.db;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by liqing on 2017/4/10 0010.
 */
public class AbstractDatasourceConfig {

    public DataSource getDruidDataSource(String clusterJDBCUrl, String clusterJDBCDriverClassName, String clusterJDBCUser, String clusterJDBCPassword) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(clusterJDBCUrl);
        dataSource.setDriverClassName(clusterJDBCDriverClassName);
        dataSource.setUsername(clusterJDBCUser);
        dataSource.setPassword(clusterJDBCPassword);
        return dataSource;
    }
}

package com.study.config.db;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 * Created by liqing on 2017/4/10 0010.
 */
public class AbstractDatasourceConfig {

    public DruidXADataSource getDruidXADataSource(String jdbcUrl, String jdbcDriverClassName, String jdbcUser, String jdbcPassword){
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(jdbcUrl);
        druidXADataSource.setDriverClassName(jdbcDriverClassName);
        druidXADataSource.setUsername(jdbcUser);
        druidXADataSource.setPassword(jdbcPassword);
        return druidXADataSource;
    }

    public AtomikosDataSourceBean getDruidDataSource(String jdbcUrl, String jdbcDriverClassName,
                                                     String jdbcUser, String jdbcPassword,
                                                     String uniqueName) {
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        DruidXADataSource dataSource = getDruidXADataSource(jdbcUrl, jdbcDriverClassName, jdbcUser, jdbcPassword);
        dataSourceBean.setXaDataSource(dataSource);
        dataSourceBean.setUniqueResourceName(uniqueName);
        return dataSourceBean;
    }
}

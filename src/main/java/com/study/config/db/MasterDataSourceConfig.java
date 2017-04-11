package com.study.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by liqing on 2017/4/6 0006.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig extends AbstractDatasourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${demo.master.datasource.url}")
    private String masterJDBCUrl;
    @Value("${demo.master.datasource.driverClassName}")
    private String masterJDBCDriverClassName;
    @Value("${demo.master.datasource.user}")
    private String masterJDBCUser;
    @Value("${demo.master.datasource.password}")
    private String masterJDBCPassword;
    @Value("${demo.master.datasource.unique-name}")
    private String masterJDBCUniqueName;

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(){
        return getDruidDataSource(masterJDBCUrl, masterJDBCDriverClassName, masterJDBCUser, masterJDBCPassword);
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource")DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}

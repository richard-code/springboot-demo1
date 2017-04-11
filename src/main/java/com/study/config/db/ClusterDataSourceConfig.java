package com.study.config.db;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by liqing on 2017/4/7 0007.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig extends AbstractDatasourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.dao.cluster";
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${db.datasource.cluster.url}")
    private String clusterJDBCUrl;
    @Value("${db.datasource.cluster.driverClassName}")
    private String clusterJDBCDriverClassName;
    @Value("${db.datasource.cluster.user}")
    private String clusterJDBCUser;
    @Value("${db.datasource.cluster.password}")
    private String clusterJDBCPassword;
    @Value("${db.datasource.cluster.uniqueName}")
    private String clusterJDBCUniqueName;

    @Bean(name = "clusterDataSource")
    public AtomikosDataSourceBean clusterDataSource(){
        return getDruidDataSource(clusterJDBCUrl, clusterJDBCDriverClassName,
                clusterJDBCUser, clusterJDBCPassword, clusterJDBCUniqueName);
    }

    /*@Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(clusterDataSource());
    }*/

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("clusterDataSource")DataSource clusterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

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

    @Value("${db.datasource.master.url}")
    private String masterJDBCUrl;
    @Value("${db.datasource.master.driverClassName}")
    private String masterJDBCDriverClassName;
    @Value("${db.datasource.master.user}")
    private String masterJDBCUser;
    @Value("${db.datasource.master.password}")
    private String masterJDBCPassword;
    @Value("${db.datasource.master..uniqueName}")
    private String masterJDBCUniqueName;

    @Primary
    @Bean(name = "masterDataSource")
    public AtomikosDataSourceBean masterDataSource(){
        return getDruidDataSource(masterJDBCUrl, masterJDBCDriverClassName,
                masterJDBCUser, masterJDBCPassword, masterJDBCUniqueName);
    }

    /*@Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }*/

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

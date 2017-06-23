package com.jusfoun.config

import com.alibaba.druid.pool.DruidDataSource
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

/**
 * Created by liutiyang on 2017/5/19.
 */
@Configuration
@ComponentScan(basePackages = arrayOf("com.jusfoun.service"))
@MapperScan(basePackages = arrayOf("com.jusfoun.dao.mapper"))
open class TestConfiguration {

    @Primary
    @Bean(name = arrayOf("dataSource"))
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun dataSource(): DataSource {
        return DruidDataSource()
    }

    @Primary
    @Bean
    open fun sqlSessionFactory(): SqlSessionFactoryBean {
        val sqlSessionFactory : SqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactory.setDataSource(dataSource())
        return sqlSessionFactory
    }
}

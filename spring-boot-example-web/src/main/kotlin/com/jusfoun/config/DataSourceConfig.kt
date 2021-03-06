package com.jusfoun.config

import com.alibaba.druid.pool.DruidDataSource
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

import javax.sql.DataSource

/**
 * Created by liutiyang on 2017/5/18.
 */
@Configuration
@MapperScan(basePackages = arrayOf("com.jusfoun.dao.mapper"))
class DataSourceConfig {

    @Primary
    @Bean(name = arrayOf("dataSource"))
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DruidDataSource()
    }

}

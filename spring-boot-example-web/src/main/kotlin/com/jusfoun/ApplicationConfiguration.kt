package com.jusfoun

import com.jusfoun.utils.SpringUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by liutiyang on 2017/5/19.
 */
@Configuration
@ComponentScan(basePackages = arrayOf("com.jusfoun.config", "com.jusfoun.service"))
class ApplicationConfiguration {

    @Bean
    fun springUtil(): SpringUtil {
        return SpringUtil()
    }
}

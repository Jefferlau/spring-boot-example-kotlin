package com.jusfoun.config

import com.alibaba.druid.support.http.StatViewServlet
import com.alibaba.druid.support.http.WebStatFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-11-11
 */
@Configuration
class DruidConfig {

    @Bean
    fun druidServlet(): ServletRegistrationBean {
        val reg = ServletRegistrationBean()
        reg.setServlet(StatViewServlet())
        reg.addUrlMappings("/druid/*")
        //reg.addInitParameter("allow", "127.0.0.1"); //白名单
        //reg.addInitParameter("deny",""); //黑名单
        reg.addInitParameter("loginUsername", "admin")
        reg.addInitParameter("loginPassword", "123456")
        //是否能够重置数据.
        reg.addInitParameter("resetEnable", "false")
        return reg
    }

    @Bean
    fun filterRegistrationBean(): FilterRegistrationBean {
        val filterRegistrationBean = FilterRegistrationBean()
        filterRegistrationBean.filter = WebStatFilter()
        filterRegistrationBean.addUrlPatterns("/*")
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")
        return filterRegistrationBean
    }
}

package com.jusfoun.config

import com.jusfoun.web.components.interceptor.MyInterceptor
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-02
 */
@Configuration
@ComponentScan(basePackages = arrayOf("com.jusfoun.web.controller", "com.jusfoun.exception"))
@ServletComponentScan(basePackages = arrayOf("com.jusfoun.web.components"))
open class WebConfig : WebMvcConfigurerAdapter() {

    @Bean
    fun myInterceptor(): MyInterceptor {
        return MyInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**")
        super.addInterceptors(registry)
    }

    /*
    @Bean
    fun myFilterRegistrationBean(): FilterRegistrationBean {
        val registrationBean = FilterRegistrationBean()

        val myFilter = MyFilter()
        registrationBean.filter = MyFilter()
        registrationBean.order = Int.MAX_VALUE
        return registrationBean
    }

    @Bean
    fun myFilter1RegistrationBean(): FilterRegistrationBean {
        val registrationBean = FilterRegistrationBean()
        registrationBean.filter = MyFilter()
        registrationBean.order = Int.MAX_VALUE
        return registrationBean
    }
    */

}

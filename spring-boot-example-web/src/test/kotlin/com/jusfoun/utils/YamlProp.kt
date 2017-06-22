package com.jusfoun.utils

/**
 * 非 Spring 容器管理的类，通过 Spring 上下文读取配置文件。
 * Create on 2017-05-26.

 * @author Jeffer Lau <jefferlzu></jefferlzu>@gmail.com>
 */
class YamlProp {

    fun readProperties() {
        // 读取配置文件
        val applicationContext = SpringUtil.getApplicationContext()
        val environment = applicationContext.environment
        val host = environment.getProperty("spring.mail.host")
        println(host)
    }
}

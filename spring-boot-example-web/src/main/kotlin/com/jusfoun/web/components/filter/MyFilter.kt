package com.jusfoun.web.components.filter

import org.slf4j.LoggerFactory
import java.io.IOException
import javax.servlet.*
import javax.servlet.annotation.WebFilter

/**
 * 当 Filter 无序时使用 ServletComponentScan 扫描即可。
 * 当 Filter 需要排序时，见 WebConfig 中的 FilterRegistrationBean 配置，
 * 此时 name 和 urlPatterns 都在 FilterRegistrationBean 里配置，order 越小越靠前。
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */

@WebFilter(filterName = "myFilter", urlPatterns = arrayOf("/*"))
class MyFilter : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        if (logger.isInfoEnabled) {
            logger.info("--- filter init ---")
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        if (logger.isInfoEnabled) {
            logger.info("--- do filter ---")
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
        if (logger.isInfoEnabled) {
            logger.info("--- filter destroy ---")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyFilter::class.java)
    }
}

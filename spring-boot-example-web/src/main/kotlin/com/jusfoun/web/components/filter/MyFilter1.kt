package com.jusfoun.web.components.filter

import org.slf4j.LoggerFactory
import java.io.IOException
import javax.servlet.*
import javax.servlet.annotation.WebFilter

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */

@WebFilter(filterName = "myFilter1", urlPatterns = arrayOf("/*"))
class MyFilter1 : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        if (logger.isInfoEnabled) {
            logger.info("--- filter1 init ---")
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        if (logger.isInfoEnabled) {
            logger.info("--- do filter1 ---")
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {
        if (logger.isInfoEnabled) {
            logger.info("--- filter1 destroy ---")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyFilter1::class.java)
    }
}

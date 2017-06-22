package com.jusfoun.web.components.interceptor

import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */
class MyInterceptor : HandlerInterceptor {

    @Throws(Exception::class)
    override fun preHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any): Boolean {
        if (logger.isInfoEnabled) {
            logger.info("--- before controller ---")
        }

        println(o.javaClass.`package`)
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, modelAndView: ModelAndView?) {
        if (logger.isInfoEnabled) {
            logger.info("--- after controller ---")
        }
    }

    @Throws(Exception::class)
    override fun afterCompletion(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, o: Any, e: Exception) {
        if (logger.isInfoEnabled) {
            logger.info("--- after DispatcherServlet ---")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyInterceptor::class.java)
    }
}

package com.jusfoun.web.components.listener

import org.slf4j.LoggerFactory
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */
@WebListener
class MyServletContextListener : ServletContextListener {

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {
        if (logger.isInfoEnabled) {
            logger.info("--- listener init ---")
        }
    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {
        if (logger.isInfoEnabled) {
            logger.info("--- listener destroy ---")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyServletContextListener::class.java)
    }
}

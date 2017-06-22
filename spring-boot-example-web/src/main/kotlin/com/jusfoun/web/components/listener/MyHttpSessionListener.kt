package com.jusfoun.web.components.listener

import org.slf4j.LoggerFactory
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */
@WebListener
class MyHttpSessionListener : HttpSessionListener {

    override fun sessionCreated(httpSessionEvent: HttpSessionEvent) {
        if (logger.isInfoEnabled) {
            logger.info("--- session create ---")
        }
    }

    override fun sessionDestroyed(httpSessionEvent: HttpSessionEvent) {
        if (logger.isInfoEnabled) {
            logger.info("--- session destroy ---")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyHttpSessionListener::class.java)
    }
}

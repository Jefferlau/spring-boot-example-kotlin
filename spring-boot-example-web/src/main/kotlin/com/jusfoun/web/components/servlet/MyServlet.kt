package com.jusfoun.web.components.servlet

import org.slf4j.LoggerFactory
import java.io.IOException
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-05
 */
@WebServlet(urlPatterns = arrayOf("/myServlet/*"), description = "Servlet 说明")
class MyServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        if (logger.isInfoEnabled) {
            logger.info("--- doGet ---")
        }
        super.doGet(req, resp)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        if (logger.isInfoEnabled) {
            logger.info("--- doPost ---")
        }
        super.doPost(req, resp)
    }

    override fun destroy() {
        if (logger.isInfoEnabled) {
            logger.info("--- destroy ---")
        }
        super.destroy()
    }

    @Throws(ServletException::class)
    override fun init(config: ServletConfig) {
        if (logger.isInfoEnabled) {
            logger.info("--- init config ---")
        }
        super.init(config)
    }

    @Throws(ServletException::class)
    override fun init() {
        if (logger.isInfoEnabled) {
            logger.info("--- init ---")
        }
        super.init()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MyServlet::class.java)

        private val serialVersionUID = 4455523440715434980L
    }
}

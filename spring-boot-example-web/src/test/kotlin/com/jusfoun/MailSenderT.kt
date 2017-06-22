package com.jusfoun

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MailSenderT {

    @Autowired
    private val mailSender: JavaMailSender? = null

    @Test
    @Throws(Exception::class)
    fun sendSimpleMail() {
        val message = SimpleMailMessage()
        message.from = "liutiyang@jusfoun.com"
        message.setTo("liutiyang@jusfoun.com")
        message.subject = "主题：简单邮件"
        message.text = "测试邮件内容"
        mailSender!!.send(message)
    }

}

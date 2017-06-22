package com.jusfoun.service

import com.jusfoun.service.impl.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Repeat
import org.springframework.test.context.junit4.SpringRunner

/**
 * UserService 单元测试
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceTests {

    @Autowired
    private val userService: UserService? = null

    @Repeat(2)
    @Test
    fun testSelectById() {
        val userId = "93201214-3b98-11e7-8b90-efc0f68ee6d7"

        val user = userService!!.selectByPrimaryKey(userId)
        assertThat(user).isNotNull()
        assertThat(user.userId).isEqualTo(userId)
        println(user)
    }
}

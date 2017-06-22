package com.jusfoun.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jusfoun.model.User
import com.jusfoun.service.impl.UserService
import com.jusfoun.web.dto.UserDto
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import java.util.UUID

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.mockito.BDDMockito.given
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * 对 Controller 进行 Mock 测试。
 * Created by liutiyang on 2017/5/22.
 */
@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTests {
    @Autowired
    private val mvc: MockMvc? = null

    private val userJacksonTester: JacksonTester<User>? = null
    private val userDtoJacksonTester: JacksonTester<UserDto>? = null

    @MockBean
    private val userService: UserService? = null

    @Before
    fun init() {
        val mapper = ObjectMapper()
        JacksonTester.initFields(this, mapper)
    }

    @Test
    @Throws(Exception::class)
    fun testGetUserInfo() {

        val userId = UUID.randomUUID().toString()

        val user = User()
        user.userId = userId
        given(userService!!.selectByPrimaryKey(userId)).willReturn(user)

        val resultActions = this.mvc!!.perform(get("/user/userInfo")
                .param("id", userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester!!.write(user).json + "}"))
                .andDo(print())

    }

    @Test
    @Throws(Exception::class)
    fun testGetUserInfoById() {

        val userId = UUID.randomUUID().toString()

        val user = User()
        user.userId = userId
        user.username = "jefferlau"
        given(userService!!.selectByPrimaryKey(userId)).willReturn(user)

        val resultActions = this.mvc!!.perform(get("/user/userInfoById")
                .param("userId", userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester!!.write(user).json + "}"))
                .andDo(print())

    }

    @Test
    @Throws(Exception::class)
    fun testGetUserInfoPathVariable() {

        val userId = UUID.randomUUID().toString()

        val user = User()
        user.userId = userId
        user.username = "jefferlau"
        given(userService!!.selectByPrimaryKey(userId)).willReturn(user)

        val resultActions = this.mvc!!.perform(get("/user/userInfo/" + userId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.userId", `is`(userId)))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":" + userJacksonTester!!.write(user).json + "}"))
                .andDo(print())

        val contentAsString = resultActions.andReturn().response.contentAsString
        assertThat(contentAsString).contains(userId)
    }

    @Test
    @Throws(Exception::class)
    fun testSaveUser() {
        val idCard = "371082199006232567"

        val userDto = UserDto()
        userDto.realName = "Jeffer Lau"
        userDto.idCard = idCard

        val user = User()
        BeanUtils.copyProperties(userDto, user)
        user.username = "jefferlau"

        given(userService!!.insertSelective(user)).willReturn(1)

        val requestBody = this.userDtoJacksonTester!!.write(userDto).json

        println(requestBody)

        val resultActions = this.mvc!!.perform(post("/user/userInfo")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.idCard", `is`(idCard)))
                .andExpect(jsonPath("$.data.userId", notNullValue()))
                .andDo(print())

        val contentAsString = resultActions.andReturn().response.contentAsString
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$")
    }

    @Test
    @Throws(Exception::class)
    fun testEditUser() {

        val user = User()
        user.username = "jefferlau"
        user.realName = "Jeffer Lau"
        user.idCard = "371082199006232567"

        given(userService!!.updateByPrimaryKeySelective(user)).willReturn(1)

        val resultActions = this.mvc!!.perform(put("/user/userInfo")
                .param("userId", UUID.randomUUID().toString())
                .param("realName", user.realName)
                .param("idCard", user.idCard)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.data.userId", notNullValue()))
                .andExpect(jsonPath("$.data.idCard", `is`(user.idCard)))
                .andDo(print())

        val contentAsString = resultActions.andReturn().response.contentAsString
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$")
    }

}

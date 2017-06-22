package com.jusfoun.web.controller

import com.jusfoun.service.impl.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.`is`
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by liutiyang on 2017/5/18.
 */
@RunWith(SpringRunner::class)
@WebMvcTest(IndexController::class)
class IndexControllerTests {
    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val userService: UserService? = null

    @Test
    @Throws(Exception::class)
    fun testIndex() {

        val resultActions = this.mvc!!.perform(get("/")
                .accept(MediaType.APPLICATION_JSON_UTF8))

        resultActions
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.code", `is`(0)))
                .andExpect(content().string("{\"code\":0,\"message\":\"success\",\"data\":null}"))
                .andDo(print())

        val contentAsString = resultActions.andReturn().response.contentAsString
        assertThat(contentAsString)
                .containsPattern("^\\{")
                .containsPattern("\\}$")
                .containsPattern("^\\{.*\\}$")
    }

}

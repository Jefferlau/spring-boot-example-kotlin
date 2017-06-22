package com.jusfoun.service.impl

import com.jusfoun.dao.mapper.UserMapper
import com.jusfoun.model.User
import com.jusfoun.model.UserExample
import com.jusfoun.service.BaseService
import org.springframework.stereotype.Service

/**
 * 用户 Service
 * Created by liutiyang on 2017/5/18.
 */
@Service
open class UserService : BaseService<UserMapper, User, UserExample>() {

    fun findByUsername(username: String): User? {
        val example = UserExample()
        example.createCriteria().andUsernameEqualTo(username)
        val userList = mapper.selectByExample(example)

        return if (userList.size > 0) userList[0] else null
    }
}

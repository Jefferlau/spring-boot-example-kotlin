package com.jusfoun.service.impl

import com.jusfoun.dao.mapper.RoleMapper
import com.jusfoun.bean.RoleExtension
import com.jusfoun.model.Role
import com.jusfoun.model.RoleExample
import com.jusfoun.service.BaseService
import org.springframework.stereotype.Service

/**
 * 角色 Service
 * Created by liutiyang on 2017/5/24.
 */
@Service
open class RoleService : BaseService<RoleMapper, Role, RoleExample>() {

    fun selectRoleExtensionByUserId(id: String) = mapper!!.selectRoleExtensionByUserId(id)
}

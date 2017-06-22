package com.jusfoun.web.controller

import com.jusfoun.exception.ExceptionMessage
import com.jusfoun.exception.RestException
import com.jusfoun.web.response.BaseResponse
import com.jusfoun.web.response.ErrorResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author 刘体阳 jefferlzu@gmail.com
 * *         Created on 2016-12-02
 */
@Api("入口相关接口")
@RestController
open class IndexController {

    @GetMapping("/")
    fun index(): BaseResponse<Any> {
        return BaseResponse<Any>()
    }

    @ApiOperation(value = "异常示例", notes = "异常时返回的数据示例。")
    @ApiResponses(ApiResponse(code = 400, message = "", response = ErrorResponse::class))
    @RequestMapping(value = "exceptionExample")
    fun exceptionExample(): BaseResponse<*> {
        throw RestException(ExceptionMessage.RealNameEmpty.code)
    }
}

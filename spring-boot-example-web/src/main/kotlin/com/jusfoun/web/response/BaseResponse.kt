package com.jusfoun.web.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * Created by liutiyang on 2017/5/16.
 */
@ApiModel(value = "基本响应模型", description = "请求响应时最基本的响应模型。")
class BaseResponse<T> {

    @ApiModelProperty(value = "响应编码，非 HTTP 状态码。", dataType = "int")
    var code: Int = 0

    @ApiModelProperty(value = "响应消息", dataType = "string")
    var message: String? = null

    @ApiModelProperty(value = "响应数据对象，不同接口返回对象可能不同。", dataType = "object")
    var data: T? = null

    init {
        code = 0
        message = "success"
    }
}

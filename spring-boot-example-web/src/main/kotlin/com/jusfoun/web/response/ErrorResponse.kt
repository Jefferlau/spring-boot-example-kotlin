package com.jusfoun.web.response

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "异常时的响应模型", description = "当请求发生异常时，返回的响应模型")
class ErrorResponse {
    @ApiModelProperty(value = "错误码，非 HTTP 响应码。", dataType = "int")
    var code: Int = 0

    @ApiModelProperty(value = "错误消息", dataType = "string")
    var message: String? = null
}

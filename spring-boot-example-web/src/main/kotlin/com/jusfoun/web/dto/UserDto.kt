package com.jusfoun.web.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jusfoun.web.validation.IdCardValid
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank

import java.io.Serializable

/**
 * Created by liutiyang on 2017/5/16.
 */
@ApiModel(value = "用户数据传输模型", description = "用于用户数据传输的模型对象")
open class UserDto : Serializable {

    @ApiModelProperty(value = "编号", dataType = "string")
    @NotBlank(message = "-30003", groups = arrayOf(Existing::class))
    var userId: String? = null

    @ApiModelProperty(value = "姓名", dataType = "string")
    @NotBlank(message = "-30004", groups = arrayOf(New::class))
    var realName: String? = null

    @ApiModelProperty(value = "密码", dataType = "string")
    @NotBlank(message = "-30005", groups = arrayOf(New::class))
    @JsonIgnore
    var password: String? = null

    @ApiModelProperty(value = "身份证号码", dataType = "string")
    @IdCardValid(message = "-30001", groups = arrayOf(New::class))
    @NotBlank(message = "-30002", groups = arrayOf(New::class))
    var idCard: String? = null

    interface Existing
    interface New

    companion object {
        private const val serialVersionUID = 4238297034619787615L
    }
}

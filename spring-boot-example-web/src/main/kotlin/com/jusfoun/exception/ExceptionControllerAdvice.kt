package com.jusfoun.exception

import com.jusfoun.web.response.ErrorResponse
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * RESTful 异常处理
 * Created by liutiyang on 2017/3/13.
 */
@ControllerAdvice
open class ExceptionControllerAdvice {

    @ExceptionHandler(RestException::class)
    fun exceptionHandler(ex: RestException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse()
        error.code = ex.errorCode
        error.message = ex.message
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun exceptionHandler(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse()
        val defaultMessage = ex.bindingResult.allErrors[0].defaultMessage
        if (StringUtils.isNumeric(defaultMessage.replace("-", ""))) {
            error.code = Integer.parseInt(defaultMessage)
            error.message = ExceptionMessage.getMessageByCode(Integer.parseInt(defaultMessage))
        } else {
            error.message = ex.bindingResult.allErrors[0].defaultMessage
        }
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun exceptionHandler(ex: BindException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse()
        val defaultMessage = ex.bindingResult.allErrors[0].defaultMessage
        if (StringUtils.isNumeric(defaultMessage.replace("-", ""))) {
            error.code = Integer.parseInt(defaultMessage)
            error.message = ExceptionMessage.getMessageByCode(Integer.parseInt(defaultMessage))
        } else {
            error.message = ex.bindingResult.allErrors[0].defaultMessage
        }
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(exception: MethodArgumentNotValidException): ErrorResponse {

        val errorMsg: String = exception.bindingResult.fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.message)

        return ErrorResponse.builder().message(errorMsg).build()
    }
    */

}

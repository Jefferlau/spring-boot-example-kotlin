package com.jusfoun.web.validation

import com.jusfoun.utils.IdcardUtils
import org.apache.commons.lang3.StringUtils

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class IdCardValidator : ConstraintValidator<IdCardValid, String> {

    override fun initialize(constraintAnnotation: IdCardValid) {

    }

    /**
     * 使用 IdCardUtils 直接验证
     */
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        if (StringUtils.isBlank(value)) {
            return true
        }
        if (!IdcardUtils.validateCard(value)) {
            return false
        }
        return true
    }


}

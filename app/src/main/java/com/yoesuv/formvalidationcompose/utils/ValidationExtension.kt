package com.yoesuv.formvalidationcompose.utils

import android.content.Context
import android.util.Patterns
import androidx.annotation.Keep
import com.yoesuv.formvalidationcompose.R

@Keep
data class ValidationModel(val isValid: Boolean, val message: String)

val regexNum = Regex("\\d+")
val patternChar = Regex("^[\\p{L} .'-]+$")
const val minChar: Int = 5

fun String.validateFullName(context: Context): ValidationModel {
    if (this.trim().isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validate_full_name_empty))
    } else if (regexNum.containsMatchIn(this)) {
        return ValidationModel(false, context.getString(R.string.validate_full_name_not_valid))
    } else if (!patternChar.matches(this)) {
        return ValidationModel(false, context.getString(R.string.validate_full_name_not_valid))
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validateEmail(context: Context): ValidationModel {
    if (this.trim().isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validate_email_empty))
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        return ValidationModel(false, context.getString(R.string.validate_email_not_valid))
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validatePassword(context: Context): ValidationModel {
    if (this.isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validate_password_empty))
    } else if (this.length < minChar) {
        return ValidationModel(false, context.getString(R.string.validate_password_min, minChar))
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validateConfirmPassword(context: Context, password: String): ValidationModel {
    if (this.isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validate_confirm_password_empty))
    } else if (this.length < minChar) {
        return ValidationModel(false, context.getString(R.string.validate_confirm_password_min, minChar))
    } else if (this != password) {
        return ValidationModel(false, context.getString(R.string.validate_confirm_password_not_match))
    } else {
        return ValidationModel(true, "")
    }
}


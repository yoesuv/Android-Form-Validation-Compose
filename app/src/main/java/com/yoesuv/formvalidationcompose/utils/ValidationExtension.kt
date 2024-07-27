package com.yoesuv.formvalidationcompose.utils

import android.util.Patterns

class ValidationModel(val isValid: Boolean, val message: String)

val regexNum = Regex("\\d+")
val patternChar = Regex("^[\\p{L} .'-]+$")

fun String.validateFullName(): ValidationModel {
    if (this.trim().isEmpty()) {
        return ValidationModel(false, "Nama kosong")
    } else if (regexNum.containsMatchIn(this)) {
        return ValidationModel(false, "Nama tidak valid")
    } else if (!patternChar.matches(this)) {
        return ValidationModel(false, "Nama tidak valid")
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validateEmail(): ValidationModel {
    if (this.trim().isEmpty()) {
        return ValidationModel(false, "Email kosong")
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        return ValidationModel(false, "Email tidak valid")
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validatePassword(): ValidationModel {
    if (this.isEmpty()) {
        return ValidationModel(false, "Password kosong")
    } else if (this.length < 5) {
        return ValidationModel(false, "Password minimal 5 karakter")
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validateConfirmPassword(password: String): ValidationModel {
    if (this.isEmpty()) {
        return ValidationModel(false, "Confirm Password kosong")
    } else if (this.length < 5) {
        return ValidationModel(false, "Confirm Password minimal 5 karakter")
    } else if (this != password) {
        return ValidationModel(false, "Confirm Password tidak sama")
    } else {
        return ValidationModel(true, "")
    }
}


package com.yoesuv.formvalidationcompose.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.formvalidationcompose.utils.validateConfirmPassword
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validateFullName
import com.yoesuv.formvalidationcompose.utils.validatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String> = _fullName
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword
    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid

    fun updateFullName(fullName: String) {
        _fullName.value = fullName
        validateRegister(fullName, _email.value, _password.value, _confirmPassword.value)
    }

    fun updateEmail(email: String) {
        _email.value = email
        validateRegister(_fullName.value, email, _password.value, _confirmPassword.value)
    }

    fun updatePassword(password: String) {
        _password.value = password
        validateRegister(_fullName.value, _email.value, password, _confirmPassword.value)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        validateRegister(_fullName.value, _email.value, _password.value, confirmPassword)
    }

    private fun validateRegister(
        fullName: String, email: String, password: String, confirmPassword: String
    ) {
        val vFullName = fullName.validateFullName(getApplication()).isValid
        val vEmail = email.validateEmail(getApplication()).isValid
        val vPassword = password.validatePassword(getApplication()).isValid
        val vConfirm = confirmPassword.validateConfirmPassword(getApplication(), password).isValid
        _isValid.value = vFullName && vEmail && vPassword && vConfirm
    }

}
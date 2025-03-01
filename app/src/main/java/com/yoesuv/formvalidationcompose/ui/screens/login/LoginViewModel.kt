package com.yoesuv.formvalidationcompose.ui.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password
    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid

    fun updateEmail(email: String) {
        _email.value = email
        validateLogin(email, _password.value)
    }

    fun updatePassword(password: String) {
        _password.value = password
        validateLogin(_email.value, password)
    }

    private fun validateLogin(email: String, password: String) {
        val app = getApplication<Application>()
        val vEmail = email.validateEmail(app).isValid
        val vPassword = password.validatePassword(app).isValid
        _isValid.value = vEmail && vPassword
    }
}
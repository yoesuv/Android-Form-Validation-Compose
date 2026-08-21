package com.yoesuv.formvalidationcompose.ui.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        val validation = email.validateEmail(getApplication<Application>())
        _uiState.update {
            it.copy(
                email = email,
                emailError = if (validation.isValid) "" else validation.message,
            )
        }
    }

    fun updatePassword(password: String) {
        val validation = password.validatePassword(getApplication<Application>())
        _uiState.update {
            it.copy(
                password = password,
                passwordError = if (validation.isValid) "" else validation.message,
            )
        }
    }
}

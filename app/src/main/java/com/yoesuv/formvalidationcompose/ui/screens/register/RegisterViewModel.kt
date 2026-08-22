package com.yoesuv.formvalidationcompose.ui.screens.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.formvalidationcompose.utils.validateConfirmPassword
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validateFullName
import com.yoesuv.formvalidationcompose.utils.validatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateFullName(fullName: String) {
        val validation = fullName.validateFullName(getApplication<Application>())
        _uiState.update {
            it.copy(
                fullName = fullName,
                fullNameError = if (validation.isValid) "" else validation.message,
            )
        }
    }

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
        val app = getApplication<Application>()
        val passwordValidation = password.validatePassword(app)
        val confirmPasswordValidation = _uiState.value.confirmPassword.validateConfirmPassword(app, password)
        _uiState.update {
            it.copy(
                password = password,
                passwordError = if (passwordValidation.isValid) "" else passwordValidation.message,
                confirmPasswordError =
                    if (confirmPasswordValidation.isValid) "" else confirmPasswordValidation.message,
            )
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        val validation =
            confirmPassword.validateConfirmPassword(
                getApplication<Application>(),
                _uiState.value.password,
            )
        _uiState.update {
            it.copy(
                confirmPassword = confirmPassword,
                confirmPasswordError = if (validation.isValid) "" else validation.message,
            )
        }
    }
}

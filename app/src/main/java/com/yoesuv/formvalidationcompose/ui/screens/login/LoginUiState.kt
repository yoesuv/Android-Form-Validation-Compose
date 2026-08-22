package com.yoesuv.formvalidationcompose.ui.screens.login

data class LoginUiState(
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
) {
    val isValid: Boolean
        get() =
            email.isNotBlank() &&
                password.isNotEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty()
}

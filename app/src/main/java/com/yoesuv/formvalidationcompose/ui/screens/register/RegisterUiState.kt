package com.yoesuv.formvalidationcompose.ui.screens.register

data class RegisterUiState(
    val fullName: String = "",
    val fullNameError: String = "",
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val confirmPassword: String = "",
    val confirmPasswordError: String = "",
) {
    val isValid: Boolean
        get() =
            fullName.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                fullNameError.isEmpty() &&
                emailError.isEmpty() &&
                passwordError.isEmpty() &&
                confirmPasswordError.isEmpty()
}

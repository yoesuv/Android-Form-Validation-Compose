package com.yoesuv.formvalidationcompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yoesuv.formvalidationcompose.ui.route.AppRoute
import com.yoesuv.formvalidationcompose.ui.screens.components.AppBasicField
import com.yoesuv.formvalidationcompose.ui.screens.components.AppPasswordFeld
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme
import com.yoesuv.formvalidationcompose.utils.validateConfirmPassword
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validateFullName
import com.yoesuv.formvalidationcompose.utils.validatePassword

@Composable
fun RegisterScreen(nav: NavHostController, modifier: Modifier = Modifier) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isValid = fullName.validateFullName().isValid &&
            email.validateEmail().isValid &&
            password.validatePassword().isValid &&
            confirmPassword.validateConfirmPassword(password).isValid

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Full Name", fontSize = 14.sp)
        AppBasicField(
            value = fullName,
            onValueChange = { fullName = it },
            errorMessage = fullName.validateFullName().message,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email", fontSize = 14.sp)
        AppBasicField(
            value = email,
            onValueChange = { email = it },
            errorMessage = email.validateEmail().message,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Password", fontSize = 14.sp)
        AppPasswordFeld(
            value = password,
            onValueChange = { password = it },
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            errorMessage = password.validatePassword().message
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Confirm Password", fontSize = 14.sp)
        AppPasswordFeld(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            errorMessage = confirmPassword.validateConfirmPassword(password).message
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            enabled = isValid,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                nav.navigate(AppRoute.Home) {
                    popUpTo<AppRoute.Login> {
                        inclusive = true
                    }
                }
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "REGISTER", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    FormValidationTheme {
        RegisterScreen(rememberNavController())
    }
}
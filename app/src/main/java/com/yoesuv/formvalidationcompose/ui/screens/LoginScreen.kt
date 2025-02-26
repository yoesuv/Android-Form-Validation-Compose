package com.yoesuv.formvalidationcompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yoesuv.formvalidationcompose.R
import com.yoesuv.formvalidationcompose.ui.route.AppRoute
import com.yoesuv.formvalidationcompose.ui.screens.components.AppBasicField
import com.yoesuv.formvalidationcompose.ui.screens.components.AppPasswordFeld
import com.yoesuv.formvalidationcompose.ui.screens.components.AppTopBar
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validatePassword

@Composable
fun LoginScreen(nav: NavHostController, viewModel: LoginViewModel = LoginViewModel()) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
//    val isValid = email.validateEmail(LocalContext.current).isValid &&
//            password.validatePassword(LocalContext.current).isValid

    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(id = R.string.login), canBack = false)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.email), fontSize = 14.sp)
            AppBasicField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                errorMessage = email.validateEmail(LocalContext.current).message,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.password), fontSize = 14.sp)
            AppPasswordFeld(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                errorMessage = password.validatePassword(LocalContext.current).message
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    nav.navigate(AppRoute.Home) {
                        popUpTo<AppRoute.Login> {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
                enabled = true
            ) {
                Text(
                    text = stringResource(id = R.string.login).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.register).uppercase(),
                    modifier = Modifier.clickable {
                        nav.navigate(AppRoute.Register)
                    })
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FormValidationTheme {
        LoginScreen(rememberNavController())
    }
}
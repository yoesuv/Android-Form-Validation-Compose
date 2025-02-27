package com.yoesuv.formvalidationcompose.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yoesuv.formvalidationcompose.R
import com.yoesuv.formvalidationcompose.ui.route.AppRoute
import com.yoesuv.formvalidationcompose.ui.screens.components.AppBasicField
import com.yoesuv.formvalidationcompose.ui.screens.components.AppPasswordFeld
import com.yoesuv.formvalidationcompose.ui.screens.components.AppTopBar
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme
import com.yoesuv.formvalidationcompose.utils.validateConfirmPassword
import com.yoesuv.formvalidationcompose.utils.validateEmail
import com.yoesuv.formvalidationcompose.utils.validateFullName
import com.yoesuv.formvalidationcompose.utils.validatePassword

@Composable
fun RegisterScreen(nav: NavHostController, viewModel: RegisterViewModel = viewModel()) {

    val mContext = LocalContext.current
    val fullName by viewModel.fullName.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val isValid by viewModel.isValid.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(id = R.string.register), canBack = true, navigateUp = {
                nav.popBackStack()
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.full_name), fontSize = 14.sp)
            AppBasicField(
                value = fullName,
                onValueChange = { viewModel.updateFullName(it) },
                errorMessage = fullName.validateFullName(mContext).message,
                imeAction = ImeAction.Next
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.email), fontSize = 14.sp)
            AppBasicField(
                value = email,
                onValueChange = { viewModel.updateEmail(it) },
                errorMessage = email.validateEmail(mContext).message,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.password), fontSize = 14.sp)
            AppPasswordFeld(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                errorMessage = password.validatePassword(mContext).message
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.confirm_password), fontSize = 14.sp)
            AppPasswordFeld(
                value = confirmPassword,
                onValueChange = { viewModel.updateConfirmPassword(it) },
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                errorMessage = confirmPassword.validateConfirmPassword(mContext, password).message
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
                Text(
                    text = stringResource(id = R.string.register).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
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
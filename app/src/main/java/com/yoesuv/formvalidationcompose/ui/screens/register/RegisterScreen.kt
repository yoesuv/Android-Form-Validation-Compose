package com.yoesuv.formvalidationcompose.ui.screens.register

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

@Composable
fun RegisterScreen(
    nav: NavHostController,
    viewModel: RegisterViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(id = R.string.register), canBack = true, navigateUp = {
                nav.popBackStack()
            })
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.full_name), fontSize = 14.sp)
            AppBasicField(
                value = uiState.fullName,
                onValueChange = { viewModel.updateFullName(it) },
                errorMessage = uiState.fullNameError,
                imeAction = ImeAction.Next,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.email), fontSize = 14.sp)
            AppBasicField(
                value = uiState.email,
                onValueChange = { viewModel.updateEmail(it) },
                errorMessage = uiState.emailError,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.password), fontSize = 14.sp)
            AppPasswordFeld(
                value = uiState.password,
                onValueChange = { viewModel.updatePassword(it) },
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                errorMessage = uiState.passwordError,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.confirm_password), fontSize = 14.sp)
            AppPasswordFeld(
                value = uiState.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                errorMessage = uiState.confirmPasswordError,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                enabled = uiState.isValid,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    nav.navigate(AppRoute.Home) {
                        popUpTo<AppRoute.Login> {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.register).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp),
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

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yoesuv.formvalidationcompose.R
import com.yoesuv.formvalidationcompose.ui.route.AppRoute
import com.yoesuv.formvalidationcompose.ui.screens.components.AppTopBar
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme

@Composable
fun HomeScreen(nav: NavHostController) {
    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(id = R.string.app_name), canBack = false)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.home_screen),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    nav.navigate(AppRoute.Login) {
                        popUpTo<AppRoute.Home> {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.back_to_login).uppercase(),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FormValidationTheme {
        HomeScreen(rememberNavController())
    }
}
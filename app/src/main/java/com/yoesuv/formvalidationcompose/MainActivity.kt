package com.yoesuv.formvalidationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yoesuv.formvalidationcompose.ui.route.AppRoute
import com.yoesuv.formvalidationcompose.ui.screens.HomeScreen
import com.yoesuv.formvalidationcompose.ui.screens.LoginScreen
import com.yoesuv.formvalidationcompose.ui.screens.RegisterScreen
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormValidationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AppRoute.Login
                ) {
                    composable<AppRoute.Login> {
                        LoginScreen(navController)
                    }
                    composable<AppRoute.Register> {
                        RegisterScreen(navController)
                    }
                    composable<AppRoute.Home> {
                        HomeScreen(navController)
                    }
                }
            }
        }
    }
}
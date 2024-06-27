package com.example.loginappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginappwithjetpackcompose.ui.screens.AuthScreen
import com.example.loginappwithjetpackcompose.ui.screens.MainScreen
import com.example.loginappwithjetpackcompose.ui.screens.SignUpScreen
import com.example.loginappwithjetpackcompose.ui.theme.LoginAppWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppWithJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(
                onLoginClick = { navController.navigate("main") },
                onSignUpClick = { navController.navigate("signup") },
                onGoogleClick = { /* Handle Google login */ },
                onForgotPasswordClick = { /* Handle forgot password */ }
            )
        }
        composable("signup") {
            SignUpScreen(
                onSignUpClick = {
                    // Navigate to the login screen after successful signup
                    navController.popBackStack("auth", inclusive = false)
                },
                onLoginClick = { navController.popBackStack() } // Go back if they already have an account
            )
        }
        composable("main") {
            MainScreen(
                onLogoutClick = {
                    navController.navigate("auth") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
    }
}

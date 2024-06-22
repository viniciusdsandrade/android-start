package com.example.loginappwithjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginappwithjetpackcompose.ui.screens.AuthScreen
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

                    SignUpScreen(
                        onSignUpClick = { Log.i("MainActivity", "SignUp") },
                        onLoginClick = { Log.i("MainActivity", "Login") })

//                    AuthScreen(
//                        onLoginClick = { Log.i("MainActivity", "Login") },
//                        onSignUpClick = { Log.i("MainActivity", "SignUp") },
//                        onGoogleClick = { Log.i("MainActivity", "Google") },
//                        onForgotPasswordClick = { Log.i("MainActivity", "Forgot Password") }
//                    )
                }
            }
        }
    }
}


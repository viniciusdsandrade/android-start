package com.example.loginappwithjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    AuthScreen(onEnterClick = {
                        Log.i("MainActivity", "User: $it")
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    onEnterClick: (User) -> Unit
) {
    val textFieldWidth = 280.dp // Defina a largura desejada aqui
    val usernameIconSize = 23.dp
    val passwordIconSize = 22.dp
    val removeRedEyeIconSize = 22.dp // Tamanho para o ícone RemoveRedEye
    val blockIconSize = 18.dp // Tamanho para o ícone Block

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        // Campo de Usuário com ícone
        Row(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .padding(4.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { newValue -> username = newValue },
                label = { Text("Usuário") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Ícone de Usuário",
                        modifier = Modifier.size(usernameIconSize)
                    )
                },
                modifier = Modifier
                    .width(textFieldWidth) // Largura fixa
                    .height(56.dp) // Altura fixa
                    .weight(1f) // Ocupa o espaço disponível no Row
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Senha com ícone e botão de visibilidade
        Row(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .padding(4.dp)
        ) {
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de Senha",
                        modifier = Modifier.size(passwordIconSize)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Block else Icons.Filled.RemoveRedEye,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            modifier = if (passwordVisible) {
                                Modifier.requiredSize(blockIconSize)
                            } else {
                                Modifier.requiredSize(removeRedEyeIconSize)
                            }
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .width(textFieldWidth) // Largura fixa
                    .height(56.dp) // Altura fixa
                    .weight(1f) // Ocupa o espaço disponível no Row
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onEnterClick(
                    User(
                        username,
                        password
                    )
                )
            }) {
            Text(text = "Entrar")
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    LoginAppWithJetpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AuthScreen(onEnterClick = {})
        }
    }
}
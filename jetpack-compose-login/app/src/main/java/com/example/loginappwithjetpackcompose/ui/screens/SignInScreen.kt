package com.example.loginappwithjetpackcompose.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginappwithjetpackcompose.TipoAutenticacao
import com.example.loginappwithjetpackcompose.User
import com.example.loginappwithjetpackcompose.ui.theme.LoginAppWithJetpackComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onEnterClick: (User) -> Unit
) {
    val textFieldWidth = 280.dp
    val usernameIconSize = 23.dp
    val passwordIconSize = 22.dp
    val removeRedEyeIconSize = 22.dp
    val blockIconSize = 18.dp

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

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
                    .width(textFieldWidth)
                    .height(56.dp)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    .width(textFieldWidth)
                    .height(56.dp)
                    .weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Cria o usuário
                val user = User(
                    "1",
                    username,
                    password,
                    "",
                    TipoAutenticacao.EMAIL
                )
                // Loga as informações do usuário
                Log.d("SignInScreen", "Usuário: $username, Senha: $password")
                // Chama a função onEnterClick com o usuário
                onEnterClick(user)
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
            SignInScreen(onEnterClick = {})
        }
    }
}
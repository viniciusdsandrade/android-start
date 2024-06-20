package com.example.loginappwithjetpackcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
                    AuthScreen(onLoginClick = {
                        Log.i("MainActivity", "SignIn")
                    },
                        onSignUpClick = {
                            Log.i("MainActivity", "SignUp")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun AuthScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
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

        val (username, setUsername) = rememberSaveable {
            mutableStateOf("")
        }
        val (password, setPassword) = rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by remember { mutableStateOf(false) }
        var checkRememberButton by remember { mutableStateOf(false) }
        val context = LocalContext.current
        // val isFieldsEmpty = username.isNotEmpty() && password.isNotEmpty()

        Image(
            painter = painterResource(id = R.drawable.logo_discerno_pet_escuro),
            contentDescription = "Logo Discerno Pet",
            modifier = Modifier
                .absolutePadding(top = 60.dp)
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .weight(1f)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .padding(4.dp)
        ) {
            TextField(
                value = username,
                onValueChange = setUsername,
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

        Row(
            modifier = Modifier
                .fillMaxWidth(0.98f)
                .padding(4.dp)
        ) {
            TextField(
                value = password,
                onValueChange = setPassword,
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

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { checkRememberButton = !checkRememberButton }) {
                    Icon(
                        imageVector = if (checkRememberButton) Icons.Filled.CheckCircle else Icons.Filled.CheckCircleOutline,
                        contentDescription = "Lembrar-me",
                        tint = if (checkRememberButton) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.width(2.dp))
                Text("Lembrar-me")
            }
            TextButton(onClick = {}) {
                Text("Esqueceu a senha?")
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(0.98f)
        ) {
            Text(text = "Entrar")
        }

        Spacer(Modifier.height(222.dp))

        AlternativeLoginOptions(
            onIconClick = { index ->
                when (index) {
                    0 -> Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(context, "Instagram Login Click", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(context, "Github Login Click", Toast.LENGTH_SHORT).show()
                }
            },
            onSignUpClick = onSignUpClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomCenter)
        )
    }
}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconSize = 42.dp
    val iconSpacing = 32.dp

    val iconList = listOf(
        R.drawable.icon_google,
        R.drawable.icon_instagram,
        R.drawable.icon_github,
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ou entre com")
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            iconList.forEachIndexed { index, iconResId ->
                if (index > 0) Spacer(Modifier.width(iconSpacing)) // Espaçamento entre ícones

                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = "Login Alternativo",
                    modifier = Modifier
                        .size(iconSize)
                        .clickable { onIconClick(index) }
                        .clip(CircleShape)
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sem Cadastro?")
            Spacer(Modifier.height(6.dp))
            TextButton(onClick = onSignUpClick) {
                Text("Cadastrar")
            }
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
            AuthScreen({}, {})
        }
    }
}
package com.example.loginappwithjetpackcompose.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginappwithjetpackcompose.R
import com.example.loginappwithjetpackcompose.ui.theme.LoginAppWithJetpackComposeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val relativePosition = 20.dp
    val usernameIconSize = 23.dp
    val passwordIconSize = 22.dp
    val removeRedEyeIconSize = 24.dp
    val blockIconSize = 18.dp
    val borderRadiusInputField = 14.dp
    val larguraInputField = 0.96f
    val alturaInputField = 56.dp
    val larguraBotaoEntrar = 0.96f

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (username, setUsername) = rememberSaveable { mutableStateOf("") }
        val (password, setPassword) = rememberSaveable { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var checkRememberButton by remember { mutableStateOf(false) }
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(46.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
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

            Text(
                text = "DISCERNOPET",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.Default,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier
                    .absolutePadding(
                        top = 20.dp,
                        bottom = 20.dp,
                    )
                    .align(Alignment.CenterHorizontally)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(larguraInputField)
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
                    .height(alturaInputField)
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(borderRadiusInputField),
                    ),
                shape = RoundedCornerShape(borderRadiusInputField),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(larguraInputField)
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
                    .height(alturaInputField)
                    .weight(1f)
                    .border(
                        width = 1.dp, // Espessura da borda (opcional)
                        color = Color.LightGray, // Cor da borda (opcional)
                        shape = RoundedCornerShape(borderRadiusInputField), // Arredondamento dos cantos
                    ),
                shape = RoundedCornerShape(borderRadiusInputField), // Arredondamento do fundo do TextField
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Cor da borda quando o TextField está focado
                    unfocusedIndicatorColor = Color.Transparent, // Cor da borda quando o TextField não está focado
                    cursorColor = Color.Black // Cor do cursor
                )
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
                Box(
                    modifier = Modifier
                        .padding(10.dp, 12.dp, 10.dp, 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { checkRememberButton = !checkRememberButton }
                ) {
                    Icon(
                        imageVector = if (checkRememberButton) Icons.Filled.CheckCircle else Icons.Filled.CheckCircleOutline,
                        contentDescription = "Lembrar-me",
                        tint = if (checkRememberButton) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    modifier = Modifier
                        .padding(0.dp, 12.dp, 12.dp, 12.dp),
                    text = "Lembrar-me",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Start
                    ),
                    color = Color.Gray
                )
            }
            Card(
                onClick = { /* Sua ação aqui */ },
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = "Esqueceu a senha?",
                    modifier = Modifier.absolutePadding(
                        right = 12.dp,
                    ),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.15.sp,
                    ),
                    color = Color.Gray
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth(larguraBotaoEntrar)
                .clip(RoundedCornerShape(0.dp)) // Arredondamento dos cantos
                .border(
                    width = 1.dp, // Espessura da borda
                    color = Color.White, // Cor da borda
                    shape = RoundedCornerShape(22.dp) // Arredondamento da borda (opcional)
                ),
            shape = RoundedCornerShape(22.dp),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Entrar",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Default,
                    color = Color.White,
                    letterSpacing = 0.15.sp
                )
            )
        }

        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            // Botão "Criar Conta"
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth(larguraBotaoEntrar)
                    .clip(RoundedCornerShape(0.dp)) // Arredondamento dos cantos
                    .border(
                        width = 1.dp, // Espessura da borda
                        color = Color.White, // Cor da borda
                        shape = RoundedCornerShape(22.dp) // Arredondamento da borda (opcional)
                    ),
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Criar Conta",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Default,
                        color = Color.White,
                        letterSpacing = 0.15.sp
                    )
                )
            }

            Spacer(Modifier.height(36.dp))

            // Botão "Login com Google"
            Button(
                onClick = onSignUpClick, // Chama o lambda do login com Google
                modifier = Modifier
                    .fillMaxWidth(larguraBotaoEntrar)
                    .clip(RoundedCornerShape(0.dp)) // Arredondamento dos cantos
                    .fillMaxWidth(1f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.LightGray) // Adiciona uma borda
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_google), // Coloque aqui o ícone do Google
                        contentDescription = "Login com Google",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(18.dp))
                    Text(
                        text = "Entrar com Google",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default,
                            color = Color.Black,
                            letterSpacing = 0.15.sp
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Adiciona espaçamento

        Spacer(Modifier.height(relativePosition))

//        AlternativeLoginOptions(
//            onIconClick = { index ->
//                when (index) {
//                    0 -> Toast.makeText(context, "Google Login Click", Toast.LENGTH_SHORT).show()
//                    1 -> Toast.makeText(context, "Instagram Login Click", Toast.LENGTH_SHORT).show()
//                    2 -> Toast.makeText(context, "Github Login Click", Toast.LENGTH_SHORT).show()
//                }
//            },
//            onSignUpClick = onSignUpClick,
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentSize(align = Alignment.BottomCenter)
//        )

        LegalNotice(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun LegalNotice(modifier: Modifier = Modifier) {
    Text(
        text = "Ao iniciar sessão, o Usuário concorda com os nossos " +
                "Termos de Serviço, Política de Privacidade e Política de Conteúdos.",
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Gray,
            letterSpacing = 0.15.sp,
            textAlign = TextAlign.Center,
            background = Color.Transparent
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
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
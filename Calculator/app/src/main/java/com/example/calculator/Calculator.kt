package com.example.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Lista de botões a serem exibidos na calculadora.
 */
val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "x",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ".", "="
)

/**
 * Composable que representa a interface da calculadora.
 *
 * @param modifier Modificador para customizar a aparência da calculadora.
 * @param viewModel ViewModel que fornece os dados e a lógica para a calculadora.
 */
@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    /**
     * Layout principal da calculadora, usando um Box para posicionar os elementos.
     */
    Box(modifier = modifier) {
        /**
         * Coluna para organizar os elementos da calculadora verticalmente.
         */
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            /**
             * Texto que exibe a equação sendo digitada pelo usuário.
             */
            Text(
                text = equationText.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            /**
             * Espaço flexível para empurrar o resultado para baixo.
             */
            Spacer(modifier = Modifier.weight(1f))
            /**
             * Texto que exibe o resultado do cálculo.
             */
            Text(
                text = resultText.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                ),
                maxLines = 5,
            )
            /**
             * Espaço fixo entre o resultado e os botões.
             */
            Spacer(modifier = Modifier.height(10.dp))
            /**
             * Grade para organizar os botões da calculadora.
             */
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(buttonList) { buttonText ->
                    /**
                     * Botão individual da calculadora.
                     */
                    CalculatorButton(btnText = buttonText, onClick = {
                        viewModel.onButtonClicked(buttonText)
                    })
                }
            }
        }
    }
}

/**
 * Função que retorna a cor do botão com base no seu texto.
 *
 * @param btnText O texto do botão.
 * @return A cor do botão.
 */
@Composable
fun getButtonColor(btnText: String): Color {
    return when (btnText) {
        "C", "AC" -> Color(0xFFEF9A9A) // Vermelho claro para botões de limpar
        "=" -> Color(0xFF2196F3) // Azul para o botão de igual
        "/", "x", "-", "+" -> Color(0xFFFF9800) // Laranja para operadores
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> Color(0xFFF2F2F2) // Cinza claro para números
        else -> Color(0xFFEEEEEE) // Cinza padrão para outros botões
    }
}

/**
 * Composable que representa um botão da calculadora.
 *
 * @param btnText O texto a ser exibido no botão.
 * @param onClick A função a ser chamada quando o botão for clicado.
 */
@Composable
fun CalculatorButton(btnText: String, onClick: () -> Unit) {
    val buttonColor = getButtonColor(btnText)

    /**
     * Container para o botão, adicionando espaçamento.
     */
    Box(modifier = Modifier.padding(10.dp)) {
        /**
         * Botão circular com estilo de Floating Action Button.
         */
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = buttonColor, // Cor de fundo do botão
            contentColor = Color.Black // Cor do texto do botão
        ) {
            /**
             * Texto do botão.
             */
            Text(
                text = btnText,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold // Texto em negrito
                )
            )
        }
    }
}
package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mozilla.javascript.Context
import java.util.Locale.US

/**
 * ViewModel para a Calculadora, responsável por gerenciar o estado da interface
 * do usuário e realizar cálculos.
 */
class CalculatorViewModel : ViewModel() {

    /**
     * MutableLiveData para o texto da equação exibido na tela da calculadora.
     */
    private val _equationText = MutableLiveData("")

    /**
     * LiveData para o texto da equação, observável pela UI.
     */
    val equationText: LiveData<String> = _equationText

    /**
     * MutableLiveData para o texto do resultado exibido na tela da calculadora.
     */
    private val _resultText = MutableLiveData("")

    /**
     * LiveData para o texto do resultado, observável pela UI.
     */
    val resultText: LiveData<String> = _resultText

    /**
     * Número atualmente sendo inserido pelo usuário.
     */
    private var currentNumber = ""

    /**
     * Operador matemático selecionado pelo usuário (+, -, *, /).
     */
    private var operator = ""

    /**
     * Sinaliza se um cálculo foi concluído recentemente.
     */
    private var calculationDone = false

    /**
     * Manipula os cliques nos botões da calculadora.
     *
     * @param button O texto do botão que foi clicado.
     */
    fun onButtonClicked(button: String) {
        when (button) {
            "C" -> clearLastCharacter()
            "AC" -> allClear()
            "=" -> calculate()
            "+", "-", "x", "/" -> handleOperator(button)
            "." -> handleDecimal()
            else -> handleNumber(button)
        }
    }

    /**
     * Realiza o cálculo da expressão matemática.
     * Utiliza uma coroutine para realizar o cálculo de forma assíncrona,
     * evitando bloqueios na thread principal.
     */
    private fun calculate() {
        viewModelScope.launch {
            try {
                val expression = prepareExpressionForEvaluation(_equationText.value ?: "")
                val result = calculateResult(expression)
                _resultText.value = formatResult(result)
                calculationDone = true
            } catch (e: Exception) {
                handleError()
            }
        }
    }

    /**
     * Adiciona um dígito ao número atual sendo inserido.
     *
     * @param number O dígito a ser adicionado.
     */
    private fun handleNumber(number: String) {
        if (calculationDone) {
            if (operator == "=") {
                _equationText.value = _resultText.value
                operator = ""
            } else {
                _equationText.value = ""
            }
            calculationDone = false
        }
        currentNumber += number
        _equationText.value += number
    }

    /**
     * Manipula a seleção de um operador matemático.
     *
     * @param newOperator O operador selecionado (+, -, *, /).
     */
    private fun handleOperator(newOperator: String) {
        if (currentNumber.isEmpty() && _resultText.value?.isNotEmpty() == true) {
            currentNumber = _resultText.value ?: ""
        }
        if (calculationDone && newOperator != "=") {
            _equationText.value = _resultText.value + " $newOperator "
            currentNumber = ""
            calculationDone = false
        } else {
            _equationText.value = "${(_equationText.value ?: "").trim()} $newOperator "
            operator = newOperator
            currentNumber = ""
        }
    }

    /**
     * Adiciona um ponto decimal ao número atual, se ainda não houver um.
     */
    private fun handleDecimal() {
        if (!currentNumber.contains(".") && currentNumber.isNotEmpty()) {
            currentNumber += "."
            _equationText.value += "."
        }
    }

    /**
     * Limpa o último caractere inserido na equação.
     */
    private fun clearLastCharacter() {
        _equationText.value = _equationText.value?.dropLast(1) ?: ""
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
        }
    }

    /**
     * Limpa completamente a calculadora, resetando todos os valores.
     */
    private fun allClear() {
        currentNumber = ""
        operator = ""
        _equationText.value = ""
        _resultText.value = ""
        calculationDone = false
    }

    /**
     * Prepara a expressão matemática para avaliação, substituindo "x" por "*"
     * para compatibilidade com o motor de cálculo.
     *
     * @param expression A expressão a ser preparada.
     * @return A expressão preparada para avaliação.
     */
    private fun prepareExpressionForEvaluation(expression: String): String {
        return expression.replace("x", "*")
    }

    /**
     * Calcula o resultado de uma expressão matemática usando o motor JavaScript Rhino.
     *
     * @param equation A equação matemática a ser calculada.
     * @return O resultado da equação como uma string.
     */
    private fun calculateResult(equation: String): String {
        val context = Context.enter()
        context.optimizationLevel = -1
        val scriptable = context.initStandardObjects()
        return context.evaluateString(scriptable, equation, "javascript", 1, null).toString()
    }

    /**
     * Formata um resultado numérico como uma string, tentando preservar a precisão
     * original e removendo zeros à direita desnecessários.
     *
     * @param resultString O resultado numérico como uma string.
     * @return O resultado formatado como uma string.
     */
    private fun formatResult(resultString: String): String {
        val result = resultString.toDoubleOrNull() ?: return "Error"
        return if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            (1..10).firstOrNull { precision ->
                val formatted = String.format(US, "%.${precision}f", result)
                formatted.toDouble() == result
            }?.let { precision ->
                String.format(US, "%.${precision}f", result)
            } ?: String.format(US, "%.15f", result)
        }
    }

    /**
     * Manipula erros durante o cálculo, exibindo uma mensagem de erro e limpando
     * o estado da calculadora.
     */
    private fun handleError() {
        _resultText.value = "Error"
        allClear()
    }
}
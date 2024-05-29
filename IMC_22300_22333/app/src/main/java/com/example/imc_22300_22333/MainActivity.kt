package com.example.imc_22300_22333

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Atividade principal do aplicativo de cálculo de IMC.
 */
class MainActivity : AppCompatActivity() {

    // Views da interface do usuário
    private lateinit var edtAltura: EditText
    private lateinit var edtPeso: EditText
    private lateinit var txtResp: TextView

    /**
     * Constantes para os limites de IMC.
     */
    companion object {
        private const val LIMITE_IMC_SUPERIOR = 29.9
        private const val LIMITE_IMC_INFERIOR = 18.5
        private const val LIMITE_IMC_IDEAL_INFERIOR = 18.5
        private const val LIMITE_IMC_IDEAL_SUPERIOR = 24.9
    }

    /**
     * Método chamado quando a atividade é criada.
     *
     * Inicializa a interface do usuário e configura o listener do botão.
     *
     * @param savedInstanceState restudy da instância salva, se houver.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()

        val btnExec = findViewById<Button>(R.id.btnExec)
        btnExec.setOnClickListener {
            calcularIMC()
        }
    }

    /**
     * Inicializa as views da interface do usuário.
     */
    private fun initializeViews() {
        edtAltura = findViewById(R.id.EdtTxAlt)
        edtPeso = findViewById(R.id.edtTxPeso)
        txtResp = findViewById(R.id.txtResp)
    }

    /**
     * Calcula o IMC com base nos valores de altura e peso inseridos.
     *
     * Obtém os valores de entrada, valida-os e exibe o resultado ou uma mensagem de erro.
     */
    private fun calcularIMC() {
        val pesoStr = edtPeso.text.toString()
        val alturaStr = edtAltura.text.toString()

        // Verifica se os campos estão vazios
        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            exibirErro("Por favor, preencha todos os campos corretamente.")
            return
        }

        try {
            val peso = pesoStr.toDouble()
            var altura = alturaStr.toDouble()

            // Valida os valores de altura e peso
            if (peso <= 0 || altura <= 0 || altura > 272 || peso > 595) {
                exibirErro("Valores inválidos para peso ou altura.")
                return
            }

            altura /= 100
            val imc = peso / (altura * altura)
            exibirResultado(imc)
        } catch (e: NumberFormatException) {
            exibirErro("Por favor, insira valores numéricos válidos para peso e altura.")
        }
    }

    /**
     * Exibe o resultado do cálculo do IMC com uma cor correspondente ao intervalo.
     *
     * @param imc o valor do IMC calculado.
     */
    private fun exibirResultado(imc: Double) {
        val imcFormatado = "%.3f".format(imc)
        val mensagem = "IMC: $imcFormatado"

        // Define a cor do texto com base no intervalo do IMC
        val cor = when {
            imc > LIMITE_IMC_SUPERIOR || imc < LIMITE_IMC_INFERIOR -> Color.RED
            imc <= LIMITE_IMC_IDEAL_INFERIOR || imc >= LIMITE_IMC_IDEAL_SUPERIOR -> Color.rgb(255, 160, 0)
            else -> Color.GREEN
        }

        txtResp.setTextColor(cor)
        txtResp.text = mensagem
        txtResp.visibility = View.VISIBLE
    }

    /**
     * Exibe uma mensagem de erro usando um Toast.
     *
     * @param mensagem a mensagem de erro a ser exibida.
     */
    private fun exibirErro(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
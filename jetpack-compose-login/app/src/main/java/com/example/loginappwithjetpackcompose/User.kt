package com.example.loginappwithjetpackcompose

import java.util.Date

/**
 * Representa um usuário do sistema.
 *
 * @property id ID do usuário.
 * @property email Endereço de email do usuário (pode ser nulo).
 * @property telefone Número de telefone do usuário (pode ser nulo).
 * @property senha Senha do usuário (obrigatório).
 * @property tipoAutenticacao Tipo de autenticação utilizado pelo usuário (obrigatório).
 */
data class User(
    val id: String,
    val email: String?,
    val senha: String,
    val telefone: String?,
    val tipoAutenticacao: TipoAutenticacao
)

/**
 * Contém informações detalhadas sobre um usuário.
 *
 * @property id ID dos detalhes do usuário.
 * @property userId ID do usuário ao qual estes detalhes pertencem.
 * @property nome Nome completo do usuário.
 * @property emailRecuperacao Endereço de email para recuperação de senha (pode ser nulo).
 * @property cpf Número de CPF do usuário (pode ser nulo).
 * @property foto URL ou caminho para a foto do usuário (pode ser nulo).
 * @property nascimento Data de nascimento do usuário (pode ser nula).
 */
data class UserDetails(
    val id: Long,
    val userId: Long,
    val nome: String,
    val emailRecuperacao: String?,
    val cpf: String?,
    val foto: String?,
    val nascimento: Date?
)

/**
 * Representa um endereço de um usuário.
 *
 * @property id ID do endereço.
 * @property usuarioId ID do usuário ao qual este endereço pertence.
 * @property cep CEP do endereço.
 * @property logradouro Logradouro do endereço.
 * @property bairro Bairro do endereço.
 * @property cidade Cidade do endereço.
 * @property estado Estado do endereço.
 * @property numero Número do endereço (pode ser nulo).
 * @property complemento Complemento do endereço (pode ser nulo).
 */
data class Endereco(
    val id: Long,
    val usuarioId: Long,
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val numero: String?,
    val complemento: String?
)

/**
 * Representa uma raça de animal de estimação.
 *
 * @property id ID da raça.
 * @property nome Nome da raça.
 */
data class Raca(
    val id: Long,
    val nome: String
)

/**
 * Representa um pet utilizado no treinamento da IA.
 *
 * @property id ID do pet de treinamento.
 * @property nome Nome do pet de treinamento (pode ser nulo).
 * @property idRaca ID da raça do pet de treinamento.
 */
data class PetTreinamentoIA(
    val id: Long,
    val nome: String?,
    val idRaca: Long
)

/**
 * Representa uma foto utilizada no treinamento da IA.
 *
 * @property id ID da foto de treinamento.
 * @property idPetTreinamentoIa ID do pet de treinamento ao qual esta foto pertence.
 * @property urlFoto URL da foto de treinamento.
 */
data class FotoTreinamento(
    val id: Long,
    val idPetTreinamentoIa: Long,
    val urlFoto: String
)

/**
 * Representa um animal de estimação de um usuário.
 *
 * @property id ID do pet.
 * @property nome Nome do pet.
 * @property foto URL ou caminho para a foto do pet.
 * @property idade Data de nascimento ou idade estimada do pet (pode ser nula).
 * @property usuarioId ID do usuário ao qual este pet pertence.
 * @property petTreinamentoIaId ID do pet de treinamento utilizado como referência para este pet.
 */
data class Pet(
    val id: Long,
    val nome: String,
    val foto: String,
    val idade: Date?,
    val usuarioId: Long,
    val petTreinamentoIaId: Long
)

/**
 * Tip's de autenticação suportados pelo sistema.
 */
enum class TipoAutenticacao {
    EMAIL, TELEFONE, GOOGLE
}
package com.example.appautohub.data.model

data class LoginResponse(
    val token: String,
    val id: Integer,
    val nome: String,
    val email: String,
    val cpfCnpj: String,
    val password: String,
    val telefone: String,
    val endereco: Endereco
)

data class Endereco(
    val id: Integer,
    val localidade: String,
    val bairro: String,
    val cep: String,
    val uf: String
)

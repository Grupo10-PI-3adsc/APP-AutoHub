package com.example.appautohub.data.model

data class LoginResponse(
    val token: String,
    val id: Long,
    val nome: String,
    val email: String
)

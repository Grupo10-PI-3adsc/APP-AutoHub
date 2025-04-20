package com.example.appautohub.data.remote

import com.example.appautohub.data.model.LoginRequest
import com.example.appautohub.data.model.LoginResponse
import com.example.appautohub.data.model.Usuario
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {

    @POST("/auth/register")
    suspend fun cadastrar(@Body usuario: Usuario): Response<Usuario> // ou o DTO certo se necessário

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

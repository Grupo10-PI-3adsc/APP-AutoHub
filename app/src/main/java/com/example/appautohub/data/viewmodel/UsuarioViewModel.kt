package com.example.appautohub.data.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appautohub.data.model.LoginRequest
import com.example.appautohub.data.model.LoginResponse
import com.example.appautohub.data.model.Usuario
import com.example.appautohub.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {

    private val api = RetrofitInstance.api

    fun cadastrarUsuario(usuario: Usuario, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.cadastrar(usuario)
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    println("Erro no cadastro: ${response.code()} - ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                println("Exceção no cadastro: ${e.message}")
                onResult(false)
            }
        }
    }

    var loginResponse by mutableStateOf<LoginResponse?>(null)
        private set

    fun loginUsuario(email: String, senha: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, password = senha)
                val response = RetrofitInstance.api.login(loginRequest)

                if (response.isSuccessful) {
                    loginResponse = response.body() // <- Aqui você armazena!
                    println("Login bem-sucedido! Token: ${loginResponse?.token}")
                    onResult(true)
                } else {
                    val erro = response.errorBody()?.string()
                    println("Erro no login: ${response.code()} - $erro")
                    onResult(false)
                }
            } catch (e: Exception) {
                println("Exceção no login: ${e.message}")
                onResult(false)
            }
        }
    }
}


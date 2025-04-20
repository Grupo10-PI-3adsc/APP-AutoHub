package com.example.appautohub.data.viewmodel


import android.util.Log
import androidx.compose.ui.platform.LocalContext
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

    fun loginUsuario(email: String, senha: String, onResult: (Boolean, LoginResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email, senha)
                val response = RetrofitInstance.api.login(loginRequest)

                if (response.isSuccessful) {
                    val token = response.body()?.token
                    println("Login bem-sucedido! Token: $token")
                    onResult(true, response.body())
                } else {
                    val erro = response.errorBody()?.string()
                    println("Erro no login: ${response.code()} - $erro")
                    onResult(false, null)
                }
            } catch (e: Exception) {
                println("Exceção no login: ${e.message}")
                e.printStackTrace() // Mostra o stacktrace no Logcat
                onResult(false, null)
            }
        }
    }


}


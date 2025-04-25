package com.example.appautohub.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appautohub.data.model.Produto
import com.example.appautohub.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProdutoViewModel : ViewModel() {

    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos: StateFlow<List<Produto>> = _produtos

    init {
        getTodosProdutos() // ← chamado automaticamente ao inicializar o ViewModel
    }

    var produtoSelecionado by mutableStateOf<Produto?>(null)

    private fun getTodosProdutos() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProdutos()
                if (response.isSuccessful && response.body() != null) {
                    _produtos.value = response.body()!!
                } else {
                    println("Erro na resposta: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Erro na requisição: ${e.message}")
            }
        }
    }
}

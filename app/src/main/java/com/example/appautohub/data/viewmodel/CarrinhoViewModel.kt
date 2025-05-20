package com.example.appautohub.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.appautohub.data.model.Produto
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CarrinhoViewModel : ViewModel() {
    private val _produtosCarrinho = MutableStateFlow<List<Produto>>(emptyList())
    val produtosCarrinho: StateFlow<List<Produto>> = _produtosCarrinho

    fun adicionarProduto(produto: Produto) {
        _produtosCarrinho.value = _produtosCarrinho.value + produto

        // Log de todos os produtos no carrinho
        Log.d("CarrinhoViewModel", "Itens no carrinho:")
        _produtosCarrinho.value.forEach {
            Log.d("CarrinhoViewModel", it.toString())
        }
        totalCarrinho
    }

    fun removerProduto(produto: Produto) {
        _produtosCarrinho.value = _produtosCarrinho.value - produto
    }

    fun limparCarrinho() {
        _produtosCarrinho.value = emptyList()
    }

    val quantidadeCarrinho = produtosCarrinho.map { it.size }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        0
    )

    val totalCarrinho: StateFlow<Double> = produtosCarrinho
        .map { produtos -> produtos.sumOf { it.preco } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

}


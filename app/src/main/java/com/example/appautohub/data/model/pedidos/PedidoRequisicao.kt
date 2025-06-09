package com.example.appautohub.data.model.pedidos

data class PedidoRequisicao(
    val carrinho: List<Int>,   // IDs dos produtos que estão no carrinho
    val intalacao: Boolean     // Se o cliente quer instalação (observação: seria "instalacao"?)
)

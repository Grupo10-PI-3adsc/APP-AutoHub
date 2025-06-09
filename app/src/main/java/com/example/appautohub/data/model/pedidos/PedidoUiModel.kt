package com.example.appautohub.data.model.pedidos

data class PedidoUiModel(
    val tipo: String,
    val data: String,
    val produtos: List<ProdutoUiModel>,  // alterado para lista de produtos
    val valor: String,
    val status: String
)

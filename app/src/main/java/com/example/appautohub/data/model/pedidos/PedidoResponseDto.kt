package com.example.appautohub.data.model.pedidos

data class PedidosResponseDto(
    val idPedido: Int,
    val clienteId: Int,
    val produtos: List<ProdutoDto>
)

data class ProdutoDto(
    val id: Int,
    val nome: String,
    val preco: Double,
    val qtdEstoque: Int
)

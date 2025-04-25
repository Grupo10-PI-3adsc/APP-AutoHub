package com.example.appautohub.data.model

import java.time.LocalDate

data class Produto(
    val id: Int? = null,
    val nome: String,
    val descricao: String,
    val categoria: String,
    val qtdEstoque: Int,
    val preco: Double,
    val fornecedor: String,
    val localizacao: String,
    val dataAtualizacao: LocalDate,
    val codBarra: String,
    val imagemUrl: String
)

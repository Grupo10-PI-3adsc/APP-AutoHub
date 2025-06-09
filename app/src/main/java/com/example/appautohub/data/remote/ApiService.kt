package com.example.appautohub.data.remote

import com.example.appautohub.data.model.LoginRequest
import com.example.appautohub.data.model.LoginResponse
import com.example.appautohub.data.model.Produto
import com.example.appautohub.data.model.Usuario
import com.example.appautohub.data.model.pedidos.PedidoRequisicao
import com.example.appautohub.data.model.pedidos.PedidosResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @POST("/api/auth/register")
    suspend fun cadastrar(@Body usuario: Usuario): Response<Usuario> // ou o DTO certo se necessário

    @POST("/api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/api/produtos/listar-produtos")
    suspend fun getProdutos(): Response<List<Produto>>

    @POST("/api/produtos/pedidos/{id}")
    suspend fun criarPedido(@Body pedido: PedidoRequisicao, @Path("id") clienteId: Int
    ): Response<PedidosResponseDto>

    @GET("/api/produtos/pedidos/usuario/{id}")
    suspend fun getPedidos(
        @Path("id") id: Int
    ): Response<List<PedidosResponseDto>>

}

package com.example.appautohub.data.remote


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.appautohub.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

var loginResponse by mutableStateOf<LoginResponse?>(null)
    private set

val id = loginResponse?.id.toString()
val nomee = loginResponse?.nome.toString()
val cpfcnpj = loginResponse?.cpfCnpj.toString()


    data class PixRequest(
        val nome: String = nomee,
        val cpf: String = cpfcnpj,
        val valor: String,
        val usuarioId: String = id,
    )

    data class PixResponse(
    val txid: String,
    val pixCopiaECola: String,
    val qrCodeImageUrl: String
    )

    data class UsuarioIdRequest(
    val usuarioId: String
    )

    data class StatusResponse(
    val txid: String,
    val status: String
    )

data class ConsultarPixRequest(
    val txid: String
)

    interface ApiPagamento {

        @POST("/pix")
        suspend fun gerarPix(@Body request: PixRequest): Response<PixResponse>

        @POST("/ConsultarPix")
        suspend fun consultarStatus(@Body body: ConsultarPixRequest): Response<StatusResponse>


    }


package com.example.appautohub.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Mostra corpo da requisição/resposta
        })
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
//             .baseUrl("http://10.18.32.13:8080")  // <- (emulador Android)
            .baseUrl("http://192.168.1.112:8080")  // <- (celular externo)
            .client(client) // <- adiciona o client com logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

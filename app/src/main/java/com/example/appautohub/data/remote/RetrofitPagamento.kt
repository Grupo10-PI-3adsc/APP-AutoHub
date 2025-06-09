package com.example.appautohub.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitPagamento {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val apiPagamento: ApiPagamento by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.112:3000/") // <- Certifique-se do final com /
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPagamento::class.java)
    }
}

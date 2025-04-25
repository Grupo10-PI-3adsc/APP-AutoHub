package com.example.appautohub

import android.app.Application
import com.example.appautohub.koin.moduloGeral
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Aplicacao: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // Opcional. Ativa mais logs do Koin no logcat

            androidContext(this@Aplicacao) // Obrigatório. Contexto da aplicação

            // indica qual(is) módulo(s) serão usados
            modules(moduloGeral)
        }
    }

}
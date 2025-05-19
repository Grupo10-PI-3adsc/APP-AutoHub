package com.example.appautohub.koin

import com.example.appautohub.data.viewmodel.CarrinhoViewModel
import com.example.appautohub.data.viewmodel.ProdutoViewModel
import com.example.appautohub.data.viewmodel.UsuarioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduloGeral = module {

    // single -> devolve a MESMA instância para todos que pedirem
    single<UsuarioViewModel> {
        UsuarioViewModel()
    }

    single<ProdutoViewModel> {
        ProdutoViewModel()
    }

    single<CarrinhoViewModel> {
        CarrinhoViewModel()
    }

}

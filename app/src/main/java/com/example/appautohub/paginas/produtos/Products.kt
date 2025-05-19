package com.example.appautohub.paginas.produtos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appautohub.data.viewmodel.ProdutoViewModel
import com.example.appautohub.ui.theme.components.*


@Composable
fun Products(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProdutoViewModel = viewModel()
) {
    val listaProdutos by viewModel.produtos.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(245, 240, 242))
    ) {
        HeaderApp(navController)

        Spacer(modifier = Modifier.height(16.dp))
        HeaderTitle2("FILTRAR PRODUTOS", navController )
        Spacer(modifier = Modifier.height(16.dp))

        // Faz a lista ocupar o máximo espaço disponível
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f) // <- aqui faz a lista crescer pra ocupar espaço entre os headers e a barra embaixo
                .padding(horizontal = 8.dp)
        ) {
            items(listaProdutos) { produto ->
                CardProduct(produto = produto, navController)
            }
        }

        // Barra de navegação fixa embaixo
        BottomNavigationBar(navController = navController)
    }
}

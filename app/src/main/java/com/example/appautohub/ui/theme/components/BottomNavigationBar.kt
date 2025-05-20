package com.example.appautohub.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.appautohub.R

import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.appautohub.data.viewmodel.CarrinhoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomNavigationBar(navController: NavController) {
    val carrinhoViewModel: CarrinhoViewModel = koinViewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp) // aumentei aqui de 60 para 74
            .background(Color.DarkGray)
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Produtos
        IconButton(onClick = { navController.navigate("produtos") }) {
            Icon(
                painter = painterResource(id = R.drawable.produtos),
                contentDescription = "Produtos",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        // Carrinho com badge animado
        IconeCarrinho(navController = navController, viewModel = carrinhoViewModel)


        // Pedidos
        IconButton(onClick = { navController.navigate("pedidos") }) {
            Icon(
                painter = painterResource(id = R.drawable.pedidos),
                contentDescription = "Pedidos",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}



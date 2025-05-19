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

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.DarkGray)
            .padding(horizontal = 24.dp),
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

        // Carrinho
        IconButton(onClick = { navController.navigate("carrinho") }) {
            Icon(
                painter = painterResource(id = R.drawable.carrinho),
                contentDescription = "Carrinho",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

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


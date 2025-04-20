package com.example.appautohub.paginas.produtos

import com.example.appautohub.ui.theme.components.HeaderApp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle
import com.example.appautohub.ui.theme.components.Product

val listaProdutos = listOf(
    Produto("Óleo para Motor", R.drawable.oleo, 49.90, "Óleo sintético 5W30 para motores."),
    Produto("Pneu Aro 15", R.drawable.oleo, 399.99, "Pneu de alta durabilidade para carros."),
    Produto("Filtro de Ar", R.drawable.oleo, 29.90, "Filtro de ar para melhor desempenho do motor."),
    Produto("Pastilha de Freio", R.drawable.oleo, 99.90, "Pastilha de freio para segurança no trânsito."),
    Produto("Bateria 60Ah", R.drawable.oleo, 499.90, "Bateria automotiva de longa duração.")
)

@Composable
fun Products(listaProdutos: List<Produto>, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(245, 240, 242))
    ) {
        HeaderApp(navController)
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTitle("PRODUTOS")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .background(Color(248, 249, 251))
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("<", fontWeight = FontWeight.Bold, color = Color(48, 50, 62), fontSize = 20.sp)
            }
            Text("Óleo", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF30323D))
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217))
            ) {
                Text(">", fontWeight = FontWeight.Bold, color = Color(48, 50, 62), fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 itens por linha
            modifier = Modifier.fillMaxSize()
        ) {
            items(listaProdutos, ) { produto ->
                Product(produto = produto, navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsPreview() {
    val navController = rememberNavController()
    AppAutoHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Products(listaProdutos, navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

package com.example.appautohub.paginas.pedidos

import com.example.appautohub.ui.theme.components.HeaderApp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle
import com.example.appautohub.ui.theme.components.Product

var oleoImage = R.drawable.oleo


val listaProdutos = listOf(
    Produto("Óleo para Motor", oleoImage, 49.90, "Óleo sintético 5W30 para motores."),
    Produto("Pneu Aro 15", oleoImage, 399.99, "Pneu de alta durabilidade para carros."),
    Produto("Filtro de Ar", oleoImage, 29.90, "Filtro de ar para melhor desempenho do motor."),
    Produto("Pastilha de Freio", oleoImage, 99.90, "Pastilha de freio para segurança no trânsito."),
    Produto("Bateria 60Ah", oleoImage, 499.90, "Bateria automotiva de longa duração.")
)

@Composable
fun Products(listaProdutos:List<Produto>, modifier: Modifier = Modifier){

    val headers = listOf("Tipo", "Data", "Produtos", "Valor", "")
    val itemsList = listOf(
        listOf("M. de obra", "01/01/2025", "2x Óleo", "R$ 50,00"),
        listOf("M. de obra", "02/01/2025", "3x Óleo ", "R$ 30,00"),
        listOf("M. de obra", "03/01/2025", "1x Óleo ", "R$ 70,00"),
        listOf("M. de obra", "04/01/2025", "1x Óleo ", "R$ 90,00"),
        listOf("M. de obra", "05/01/2025", "5x Óleo ", "R$ 120,00")
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 240, 242))
    )
    {
        HeaderApp()
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTitle("PEDIDOS")
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // 4 colunas
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    headers.forEach { header ->
                        Text(
                            text = header,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            items(itemsList) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(217, 217, 217))
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item.forEach { data ->
                        Text(
                            text = data,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .weight(1f)

                            )
                    }
                    Button(
                        onClick = { /* Ação ao clicar no botão */ },
                        colors = ButtonDefaults.buttonColors(
                            Color(220, 187, 69)
                        ),
                    ) {
                        Text(
                            "Cancelar",
                            color = Color.Black,
                            fontSize = 15.sp)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppAutoHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Products(listaProdutos , modifier = Modifier.padding(innerPadding))
        }
    }
}

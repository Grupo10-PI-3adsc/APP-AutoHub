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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle
import com.example.appautohub.ui.theme.components.Product


@Composable
fun PedidoScreen(navController: NavController){

    val headers = listOf("Tipo", "Data", "Prod.", "Valor", "")
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
    ) {
        HeaderApp(navController)
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTitle("PEDIDOS", navController)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    headers.forEach { header ->
                        Text(
                            text = header,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF30323E),
                            modifier = Modifier
                                .padding(horizontal = 11.dp), // Espaçamento para ajustar a separação
                            textAlign = TextAlign.Start // Alinhamento à esquerda
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
                            color = Color(0xFF30323E), // Cor aplicada
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Button(
                        onClick = { /* Ação ao clicar no botão */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFDDBB45)
                        ),
                    ) {
                        Text(
                            "Cancelar",
                            color = Color(0xFF30323D),
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

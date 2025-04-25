package com.example.appautohub.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appautohub.data.model.Produto
import com.example.appautohub.data.viewmodel.ProdutoViewModel
import com.example.appautohub.paginas.produtos.ProdutoExpandido
import org.koin.compose.koinInject

@Composable
fun Product(produto: Produto, navController: NavController, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var produtoSelection = koinInject<ProdutoViewModel>()

    Card(
        modifier = modifier
            .width(180.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(245, 240, 242)),
        ) {
            AsyncImage(
                model = produto.imagemUrl, // Agora é uma URL
                contentDescription = produto.nome,
                modifier = Modifier
                    .width(180.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        isExpanded = true // Quando a imagem for clicada, expande
                        produtoSelection.produtoSelecionado = produto
                        navController.navigate("produtoExpandido")

                    },
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .background(Color(217, 217, 217))
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                text = produto.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(48, 50, 62)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(232, 197, 71)),
            ) {
                Text(
                    text = "R$ ${produto.preco}",
                    color = Color(48, 50, 62),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center, // Alinha o texto do preço no centro
                    modifier = Modifier.padding(all = 8.dp) // Espaçamento inferior
                )

                Button(
                    onClick = {
                        navController.navigate("payment")
                    },
                    colors = ButtonDefaults.buttonColors(
                        Color(48, 50, 62)
                    ),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Comprar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(245, 240, 242))
                }
            }
        }
    }
}



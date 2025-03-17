package com.example.appautohub.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.max
import com.example.appautohub.classes.Produto

@Composable
fun Product(produto: Produto, modifier: Modifier = Modifier) {
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
            Image(
                modifier = Modifier
                    .width(180.dp) // Aumentei o tamanho da imagem para se destacar mais
                    .height(360.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = produto.imagemResId),
                contentDescription = produto.nome,
                contentScale = ContentScale.Crop // Garante que a imagem se ajuste ao tamanho
            )


            Text(
                modifier = Modifier.padding(bottom = 4.dp)
                .background(Color(217, 217, 217))
                    .padding(all = 8.dp,)
                .fillMaxWidth(),
                text = produto.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(48, 50, 62)

            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(232, 197, 71)),
            ){
                Text(
                    text = "R$ ${produto.preco}",
                    color = Color(48, 50, 62),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center, // Alinha o texto do preço no centro
                    modifier = Modifier.padding(all = 8.dp) // Espaçamento inferior
                )

                Button(
                    onClick = { /* Ação ao clicar no botão */ },
                    colors = ButtonDefaults.buttonColors(
                        Color(48, 50, 62)
                    ),
                    modifier = Modifier
                        .padding(bottom =  8.dp)
                        .align(Alignment.CenterHorizontally)
                        // Adiciona padding no botão
                ) {
                    Text("Comprar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(245, 240, 242)) // Define o estilo do texto
                }
            }
        }
    }
}

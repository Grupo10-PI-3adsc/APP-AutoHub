package com.example.appautohub.paginas.carrinho

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appautohub.data.viewmodel.CarrinhoViewModel
import com.example.appautohub.ui.theme.components.HeaderApp
import org.koin.compose.koinInject


@Composable
fun CartScreen(navController: NavController) {
    val viewModel = koinInject<CarrinhoViewModel>()
    val cartItems by viewModel.produtosCarrinho.collectAsState()
    val total by viewModel.totalCarrinho.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F1F1))
    ) {
        HeaderApp(navController)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "ITENS NO CARRINHO",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF30323D)
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(cartItems) { produto ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = produto.imagemUrl,
                                contentDescription = produto.nome,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(produto.nome, fontSize = 16.sp, color = Color(0xFF30323D))
                                Text(
                                    "R$%.2f".format(produto.preco),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF30323D)
                                )
                            }
                            IconButton(onClick = { viewModel.removerProduto(produto) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Remover", tint = Color(0xFF30323D))
                            }
                        }
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF30323D))
                    Text("R$%.2f".format(total), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF30323D))
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { /* TODO: ação de pagamento */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4C04F))
                    ) {
                        Text("Pagar", fontSize = 16.sp, color = Color(0xFF30323D))
                    }
                    Button(
                        onClick = { viewModel.limparCarrinho() },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C2D38))
                    ) {
                        Text("Esvaziar", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

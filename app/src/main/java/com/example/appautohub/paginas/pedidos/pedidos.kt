package com.example.appautohub.paginas.pedidos

import com.example.appautohub.ui.theme.components.HeaderApp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.appautohub.data.viewmodel.PedidoViewModel
import com.example.appautohub.data.viewmodel.UsuarioViewModel
import com.example.appautohub.ui.theme.components.HeaderTitle
import org.koin.compose.koinInject

@Composable
fun PedidoScreen(navController: NavController) {
    val viewModel = koinInject<PedidoViewModel>()
    val usuarioViewModel = koinInject<UsuarioViewModel>()
    val pedidos by viewModel.pedidosUsuario.collectAsState()

    val usuarioId = usuarioViewModel.loginResponse?.id ?: 0

    LaunchedEffect(Unit) {
        viewModel.buscarPedidosPorUsuario(usuarioId.toInt())
    }

    val headers = listOf("Tipo", "Data", "Valor", "Status")

    Column(
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
                                .padding(horizontal = 11.dp)
                                .weight(1f),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            items(pedidos) { pedido ->
                var expanded by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(217, 217, 217))
                        .padding(10.dp)
                        .clickable { expanded = !expanded }  // <-- Linha toda clicável
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(pedido.tipo, fontSize = 13.sp, color = Color(0xFF30323E), modifier = Modifier.weight(1f))
                        Text(pedido.data, fontSize = 13.sp, color = Color(0xFF30323E), modifier = Modifier.weight(1f))
                        Text(pedido.valor, fontSize = 13.sp, color = Color(0xFF30323E), modifier = Modifier.weight(1f))
                        Text(
                            pedido.status,
                            fontSize = 13.sp,
                            color = Color(0xFF30323E),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = false
                        )

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (expanded) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            pedido.produtos.forEach { produto ->
                                Text(
                                    text = "${produto.nome} - R$ ${"%.2f".format(produto.preco)}",
                                    fontSize = 14.sp,
                                    color = Color(0xFF50535F),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

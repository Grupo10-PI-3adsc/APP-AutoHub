package com.example.appautohub.paginas.produtos

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appautohub.data.viewmodel.CarrinhoViewModel
import com.example.appautohub.data.viewmodel.ProdutoViewModel
import org.koin.compose.koinInject




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutoExpandido(navController: NavController) {


    val produto = koinInject<ProdutoViewModel>()
    val productSelect = produto.produtoSelecionado
    val carrinhoViewModel = koinInject<CarrinhoViewModel>()


    if (productSelect != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xF5F0F2).copy(alpha = 0.6f)) // Fundo com 60% de opacidade
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    .background(Color.White)
                    .padding(bottom = 16.dp),
                containerColor = Color.White,
                bottomBar = {
                    Button(
                        onClick = {
                            Log.d("ProdutoExpandido", "Adicionando produto: $productSelect")
                            carrinhoViewModel.adicionarProduto(productSelect)
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(horizontal = 16.dp, vertical = 2.dp)
                            .padding(bottom = 16.dp), // Ajuste o valor do padding conforme necessário
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF30323E) // Cor de fundo do botão
                        )
                    ) {
                        Text("Adicionar ao Carrinho",
                            color = Color.White)
                    }
                }
            ) { innerPadding ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                        }
                    }


                    // Imagem do produto
                    AsyncImage(
                        model = productSelect.imagemUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Nome e preço
                    Text(
                        text = productSelect.nome,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "R$ %.2f".format(productSelect.preco),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Text(
                        text = "R$ %.2f".format(productSelect.preco / 10) + " em 10x",
                        fontSize = 10.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    // Descrição
                    Text(
                        text = productSelect.descricao,
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

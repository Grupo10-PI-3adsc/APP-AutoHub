package com.example.appautohub.paginas.produtos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.appautohub.data.model.Produto
import com.example.appautohub.data.viewmodel.ProdutoViewModel
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.*

@Composable
fun Products(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProdutoViewModel = viewModel()
) {
    val listaProdutos by viewModel.produtos.collectAsState()

    // Pegando categorias únicas dos produtos
    val categorias = listaProdutos.map { it.categoria }.distinct()

    // Estado para controlar qual categoria está selecionada
    var categoriaSelecionadaIndex by remember { mutableStateOf(0) }

    // Pegar categoria atual com segurança
    val categoriaSelecionada = categorias.getOrNull(categoriaSelecionadaIndex) ?: ""

    // Filtrando produtos pela categoria atual
    val produtosFiltrados = listaProdutos.filter { it.categoria == categoriaSelecionada }

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
                onClick = {
                    if (categoriaSelecionadaIndex > 0) categoriaSelecionadaIndex--
                },
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("<", fontWeight = FontWeight.Bold, color = Color(48, 50, 62), fontSize = 20.sp)
            }

            Text(
                text = categoriaSelecionada.ifBlank { "Todas" },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF30323D)
            )

            Button(
                onClick = {
                    if (categoriaSelecionadaIndex < categorias.size - 1) categoriaSelecionadaIndex++
                },
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
            ) {
                Text(">", fontWeight = FontWeight.Bold, color = Color(48, 50, 62), fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(produtosFiltrados) { produto ->
                Product(produto = produto, navController)
            }
        }
    }
}


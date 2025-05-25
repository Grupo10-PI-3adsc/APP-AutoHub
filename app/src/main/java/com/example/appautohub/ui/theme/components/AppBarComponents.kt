package com.example.appautohub.ui.theme.components


import com.example.appautohub.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appautohub.data.viewmodel.CarrinhoViewModel

@Composable
fun IconeCarrinho(navController: NavController, viewModel: CarrinhoViewModel) {
    val quantidade by viewModel.quantidadeCarrinho.collectAsState()

    IconButton(
        onClick = { navController.navigate("carrinho") },
        modifier = Modifier.size(50.dp)
    ) {
        BadgedBox(
            badge = {
                AnimatedVisibility(visible = quantidade > 0) {
                    Badge(modifier = Modifier.absoluteOffset(y=0.dp, x = -15.dp)) {
                        Text("$quantidade", fontSize = 14.sp)
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.carrinho),
                contentDescription = "Carrinho",
                tint = Color.White
            )
        }
    }
}


package com.example.appautohub.ui.theme.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appautohub.R
import com.example.appautohub.data.model.Usuario
import com.example.appautohub.data.viewmodel.UsuarioViewModel
import com.example.appautohub.paginas.produtos.Products
import org.koin.compose.koinInject

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun HeaderApp(navController: NavController?) {
    val viewModel = koinInject<UsuarioViewModel>()
    val userProfile = viewModel.loginResponse

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.DarkGray)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo no canto esquerdo
        Image(
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController?.navigate("produtos")
                },
            painter = painterResource(id = R.drawable.lotus),
            contentDescription = "Logo lotus"
        )

        // Bloco com imagem de perfil + nome do usuário no canto direito
        if (userProfile != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        navController?.navigate("perfil")
                    }
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navController?.navigate("carrinho")
                        },
                    painter = painterResource(id = R.drawable.carrinho),
                    contentDescription = "Carrinho"
                )

                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            navController?.navigate("perfil")
                        },
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Perfil"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = userProfile.nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .clickable {
                            navController?.navigate("perfil")
                        }
                )
            }
        }
    }
}



@Composable
fun HeaderTitle2(title: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { navController.navigate("allprodutos") }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
        }
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF30323D),
            modifier = Modifier.align(Alignment.Center) // Centraliza o título independentemente do ícone
        )
    }
}


@Composable
fun HeaderTitle(title: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF30323D),
            modifier = Modifier.align(Alignment.Center) // Centraliza o título independentemente do ícone
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.navigate("produtos") }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }
        }

    }
}

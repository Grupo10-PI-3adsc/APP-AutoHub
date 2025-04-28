package com.example.appautohub.paginas.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.example.appautohub.R
import com.example.appautohub.data.viewmodel.UsuarioViewModel
import com.example.appautohub.ui.theme.components.HeaderApp
import org.koin.compose.koinInject

@Composable
fun PerfilScreen(navController: NavController) {
    val usuarioViewModel = koinInject<UsuarioViewModel>()
    val usuarioLogado = usuarioViewModel.loginResponse

    Box(
        modifier = Modifier
            .background(Color(0xFFF5F0F2))
            .fillMaxSize()
    ) {
        HeaderApp(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Botão de voltar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Ícone de usuário
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8C547)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Foto de Perfil",
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (usuarioLogado != null) {
                Text(
                    text = usuarioLogado.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF30323E)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    PerfilInfo(label = "Nome", valor = usuarioLogado.nome)
                    PerfilInfo(label = "Email", valor = usuarioLogado.email)
                    PerfilInfo(label = "Telefone", valor = usuarioLogado.telefone)
                    PerfilInfo(label = "CPF", valor = usuarioLogado.cpfCnpj)
                    PerfilInfo(label = "Cidade", valor = (usuarioLogado.endereco.localidade ?: "Não informado").toString())
                    PerfilInfo(label = "Estado", valor = usuarioLogado.endereco.uf ?: "Não informado")
                    PerfilInfo(label = "Bairro", valor = usuarioLogado.endereco.bairro ?: "Não informado")
                    PerfilInfo(label = "CEP", valor = usuarioLogado.endereco.cep ?: "Não informado")
                }
            } else {
                Text(
                    text = "Usuário não encontrado.",
                    fontSize = 18.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp)
                )
            }

            Text(
                text = "Meus pedidos",
                fontSize = 14.sp,
                color = Color(0xFF30323E),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate("pedidos")
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* ação de logout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF30323E),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sair", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun PerfilInfo(label: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF30323E),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = valor,
            fontSize = 14.sp,
            color = Color(0xFF30323E),
            textAlign = TextAlign.End
        )
    }
}

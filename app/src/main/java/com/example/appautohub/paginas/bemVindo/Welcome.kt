package com.example.appautohub.paginas.bemVindo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.ui.theme.AppAutoHubTheme


@Composable
fun WelcomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    val preto = Color(0xFF30323D)
    val amarelo = Color(0xFFE8C547)
    val branco = Color(0xFFFFF9FB)


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagem_cadastro), // Substitua pela sua imagem de fundo
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lotus_fundo),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(branco)
                    .padding(24.dp)
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bem vindo!",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
                        color = Color.Black
                    )

                    Text(
                        text = "Encontre o melhor autocenter para sua necessidade.",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("login") },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = preto)
                        ) {
                            Text(text = "Login", fontSize = 16.sp, color = branco)
                        }
                        Button(
                            onClick = { navController.navigate("register") },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = amarelo)
                        ) {
                            Text(text = "Cadastro", fontSize = 16.sp, color = preto)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()

    AppAutoHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WelcomeScreen(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}
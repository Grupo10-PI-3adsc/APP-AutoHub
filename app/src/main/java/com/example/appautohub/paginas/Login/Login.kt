package com.example.appautohub.paginas.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appautohub.R
import com.example.appautohub.data.viewmodel.UsuarioViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("victor2@adm.com") }
    var senha by remember { mutableStateOf("senha123") }
    val viewModel = koinInject<UsuarioViewModel>()
    val context = LocalContext.current

    val preto = Color(0xFF30323D)
    val amarelo = Color(0xFFE8C547)
    val branco = Color(0xFFFFF9FB)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

//        navController.navigate("login") {
//            popUpTo("welcome") { inclusive = true }
//        }

        Image(
            painter = painterResource(id = R.drawable.imagem_cadastro),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(branco)
                    .padding(24.dp)
                    .height(370.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF30323D),
                        modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Color(0xFF30323D)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text("Senha", color = Color(0xFF30323D)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { /* TODO: Implementar recuperação de senha */ }) {
                            Text(text = "Esqueceu a senha?", fontSize = 16.sp, color = preto)
                        }
                    }

                    Button(
                        onClick = {


                            viewModel.loginUsuario(email, senha) { sucesso ->
                                if (sucesso) {
                                    val token = viewModel.loginResponse?.token
                                    Toast.makeText(context, "Token: $token", Toast.LENGTH_LONG).show()
                                    navController.navigate("produtos")
                                } else {
                                    Toast.makeText(context, "Login falhou", Toast.LENGTH_SHORT).show()
                                }
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = amarelo)
                    ) {
                        Text(text = "Fazer Login", fontSize = 20.sp, color = preto)
                    }

                }
            }
        }
    }
}


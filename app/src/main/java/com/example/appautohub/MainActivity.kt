package com.example.appautohub

import CartScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.paginas.Cadastro.RegisterScreen
import com.example.appautohub.paginas.bemVindo.WelcomeScreen
import com.example.appautohub.paginas.Login.LoginScreen
import com.example.appautohub.paginas.boleto.BoletoScreen
import com.example.appautohub.paginas.card.CardScreen
import com.example.appautohub.paginas.payment.PaymentScreen
import com.example.appautohub.paginas.pedidos.PedidoScreen
import com.example.appautohub.paginas.perfil.PerfilScreen
import com.example.appautohub.paginas.pix.Pix
import com.example.appautohub.paginas.produtos.Products
import com.example.appautohub.paginas.produtos.ProdutoExpandido
import com.example.appautohub.ui.theme.AppAutoHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAutoHubTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "welcome",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("welcome") { WelcomeScreen(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("register") { RegisterScreen(navController) }
                        composable("payment") { PaymentScreen(navController) }
                        composable("pix") { Pix(navController) }
                        composable("cartao") { CardScreen(navController) }
                        composable("boleto") { BoletoScreen(navController) }
                        composable("produtos" ) {
                            Products(
                                navController = navController
                            )
                        }
                        composable("produtoExpandido") { ProdutoExpandido(navController) }
                        composable("carrinho") { CartScreen(navController)}
                        composable("perfil") { PerfilScreen(navController) }
                        composable("pedidos") { PedidoScreen(navController) }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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


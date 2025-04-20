package com.example.appautohub.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.paginas.BemVindo.WelcomeScreen
import com.example.appautohub.paginas.Cadastro.RegisterScreen
import com.example.appautohub.paginas.Login.LoginScreen
import com.example.appautohub.paginas.boleto.BoletoScreen
import com.example.appautohub.paginas.card.CardScreen
import com.example.appautohub.paginas.payment.PaymentScreen
import com.example.appautohub.paginas.pix.Pix
import com.example.appautohub.paginas.produtos.Products

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    val listaProdutos = listOf(
        Produto("Óleo para Motor", R.drawable.oleo, 49.90, "Óleo sintético 5W30 para motores."),
        Produto("Pneu Aro 15", R.drawable.oleo, 399.99, "Pneu de alta durabilidade para carros."),
        Produto("Filtro de Ar", R.drawable.oleo, 29.90, "Filtro de ar para melhor desempenho do motor."),
        Produto("Pastilha de Freio", R.drawable.oleo, 99.90, "Pastilha de freio para segurança no trânsito."),
        Produto("Bateria 60Ah", R.drawable.oleo, 499.90, "Bateria automotiva de longa duração.")
    )


    NavHost(navController = navController, startDestination = "payment") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
        composable("pix") { Pix(navController) }
        composable("cartao") { CardScreen(navController) }
        composable("boleto") { BoletoScreen(navController) }
        composable("produtos" ) { Products(listaProdutos, navController = navController) }
    }
}

package com.example.appautohub.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.paginas.bemVindo.WelcomeScreen
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


    NavHost(navController = navController, startDestination = "payment") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("payment") { PaymentScreen(navController) }
        composable("pix") { Pix(navController) }
        composable("cartao") { CardScreen(navController) }
        composable("boleto") { BoletoScreen(navController) }
        composable("produtos" ) { Products(navController = navController) }
    }
}

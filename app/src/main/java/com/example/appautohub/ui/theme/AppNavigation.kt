package com.example.appautohub.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.appautohub.classes.Produto
import com.example.appautohub.paginas.BemVindo.WelcomeScreen
import com.example.appautohub.paginas.Cadastro.RegisterScreen
import com.example.appautohub.paginas.Login.LoginScreen
import com.example.appautohub.paginas.boleto.BoletoScreen
import com.example.appautohub.paginas.card.CardScreen
import com.example.appautohub.paginas.payment.PaymentScreen
import com.example.appautohub.paginas.pix.Products

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen() }
        composable("register") { RegisterScreen() }
        composable("payment") { PaymentScreen(navController) }
        composable("pix") {  }
        composable("cartao") { CardScreen(navController) }
        composable("boleto") { BoletoScreen(navController) }
//        composable("produtos" ) {Produto(null, navController)}
    }
}

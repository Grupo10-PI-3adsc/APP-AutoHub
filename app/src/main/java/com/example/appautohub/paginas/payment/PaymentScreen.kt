package com.example.appautohub.paginas.payment

import com.example.appautohub.ui.theme.components.HeaderApp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle

@Composable
fun PaymentScreen(navController: NavController, modifier: Modifier = Modifier) {
    var selectedMethod by remember { mutableStateOf(0) }

    val paymentMethods = listOf("Pix", "Cartão de crédito", "Boleto")
    var paymentIcons = listOf(R.drawable.pix, R.drawable.credit_card, R.drawable.barcode)

    val items = listOf(
        Pair("Óleo Lubramax", 38.00),
        Pair("Óleo Lubramax", 38.00)
    )

    val total = items.sumOf { it.second }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {

        HeaderApp(navController)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            HeaderTitle("MÉTODO DE PAGAMENTO", navController)

            Spacer(modifier = Modifier.height(16.dp))

            paymentMethods.forEachIndexed { index, method ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable { selectedMethod = index } // Define o método de pagamento selecionado
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = paymentIcons[index]),
                        contentDescription = "Ícone de pagamento",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(method, fontSize = 16.sp, color = Color(0xFF30323D))
                    Spacer(modifier = Modifier.weight(1f))
                    RadioButton(
                        selected = selectedMethod == index,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF30323D),
                            unselectedColor = Color(0xFF30323D)
                        ),
                        onClick = null // Remove a necessidade de clique aqui, já que o `Row` é clicável
                    )

                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text("ITENS:", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF30323D))
            items.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.first, fontSize = 14.sp, color = Color(0xFF30323D))
                    Text("R$%.2f".format(item.second), fontSize = 14.sp, color = Color(0xFF30323D))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF30323D))
                Text("R$%.2f".format(total), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF30323D))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    when (selectedMethod) {
                        0 -> navController.navigate("pix")
                        1 -> navController.navigate("cartao")
                        2 -> navController.navigate("boleto")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(232, 197, 71))
            ) {
                Text("Prosseguir", fontSize = 16.sp, color = Color(0xFF30323D))
            }

        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController() // Criando um NavController para a preview

    AppAutoHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PaymentScreen(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}


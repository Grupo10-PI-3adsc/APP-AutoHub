package com.example.appautohub.Pagamento

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.appautohub.R
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle

@Composable
fun PaymentScreen(modifier: Modifier = Modifier) {
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

        HeaderApp()

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

            HeaderTitle("MÉTODO DE PAGAMENTO")

            Spacer(modifier = Modifier.height(16.dp))

            paymentMethods.forEachIndexed { index, method ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable { selectedMethod = index }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = paymentIcons[index]),
                        contentDescription = "Ícone de pagamento",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(method, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    RadioButton(
                        selected = selectedMethod == index,
                        onClick = { selectedMethod = index}
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text("ITENS:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            items.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.first, fontSize = 14.sp)
                    Text("R$%.2f".format(item.second), fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("R$%.2f".format(total), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {
                Text("Prosseguir", fontSize = 16.sp, color = Color.Black)
            }
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppAutoHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PaymentScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

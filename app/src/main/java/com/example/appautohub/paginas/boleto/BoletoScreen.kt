package com.example.appautohub.paginas.boleto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.paginas.card.CardScreen
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderApp
import com.example.appautohub.ui.theme.components.HeaderTitle

@Composable
fun BoletoScreen(navController: NavController) {
    var boleto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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

                HeaderTitle("BOLETO")

                Spacer(modifier = Modifier.height(32.dp))

                Text("Pague de forma segura e instantânea!\n" +
                        "Ao finalizar o pedido, você verá o código para fazer o pagamento.\n" +
                        "Ao continuar, você concorda com nossos Termos e Condições.", color = Color(0xFF30323D))

                Spacer(modifier = Modifier.height(25.dp))

                Text("Código de barras", color = Color(0xFF30323D))
                Row {
                    OutlinedTextField(
                        value = boleto,
                        onValueChange = { boleto = it },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.paste),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp) // Tamanho do ícone
                            )
                        },
                        label = { Text("Boleto", color = Color(0xFF30323D)) }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    "Para pagar com boleto, gere o documento ou copie o código de barras ou baixe o arquivo. " +
                            "Em seguida, use o app do seu banco ou vá até uma agência bancária ou casa lotérica " +
                            "para realizar o pagamento. Após a compensação, que pode levar até 3 dias úteis, o " +
                            "status do pedido será atualizado automaticamente no app.", color = Color(0xFF30323D)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(232, 197, 71))
                ) {
                    Text("Efetuar Pagamento", fontSize = 16.sp, color = Color(0xFF30323D))
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
        BoletoScreen(navController)
    }
}
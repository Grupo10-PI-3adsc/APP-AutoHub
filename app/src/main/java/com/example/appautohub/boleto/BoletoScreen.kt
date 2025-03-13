package com.example.appautohub.boleto

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
import com.example.appautohub.R
import com.example.appautohub.card.CardScreen
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderApp
import com.example.appautohub.ui.theme.components.HeaderTitle

@Composable
fun BoletoScreen() {
    var boleto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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

                HeaderTitle("BOLETO")

                Spacer(modifier = Modifier.height(32.dp))

                Text("Pague de forma segura e instantânea!\n" +
                        "Ao finalizar o pedido, você verá o código para fazer o pagamento.\n" +
                        "Ao continuar, você concorda com nossos Termos e Condições.")

                Spacer(modifier = Modifier.height(25.dp))

                Text("Código de barras")
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
                        label = { Text("Boleto") }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    "Para pagar com boleto, gere o documento ou copie o código de barras ou baixe o arquivo. " +
                            "Em seguida, use o app do seu banco ou vá até uma agência bancária ou casa lotérica " +
                            "para realizar o pagamento. Após a compensação, que pode levar até 3 dias úteis, o " +
                            "status do pedido será atualizado automaticamente no app."
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                ) {
                    Text("Efetuar Pagamento", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppAutoHubTheme {
        BoletoScreen()
    }
}
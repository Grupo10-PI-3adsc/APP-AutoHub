package com.example.appautohub.paginas.pix

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import com.example.appautohub.ui.theme.components.HeaderApp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appautohub.R
import com.example.appautohub.classes.Produto
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderTitle

var oleoImage = R.drawable.oleo




@Composable
fun Pix(navController: NavController, modifier: Modifier = Modifier){

    var qrcode = R.drawable.pix_qrcode

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(245, 240, 242))
    )
    {
        HeaderApp(navController)
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTitle("PIX", navController)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text("Pague de forma segura e instantânea!\n" +
                    "Ao finalizar o pedido, você verá o código para fazer o pagamento.\n" +
                    "Ao continuar, você concorda com nossos Termos e Condições.",
                fontSize = 13.sp,
                color = Color(0xFF30323D),
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Simple
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp),
                painter = painterResource(id = qrcode),
                contentDescription = "qrcode"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Chave pix:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 5.dp),
                    color = Color(0xFF30323D)
                )
                Text(
                    text = "A9823891HDHCDSCSA210312",
                    fontSize = 13.sp,
                    color = Color(0xFF30323D),
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp,
                            Color(234, 205, 25)),
                            shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text("Para pagar com QR Code, basta escanear o código exibido na tela usando o app do seu banco ou carteira digital, conferir os dados e confirmar. Já com o Pix Copia e Cola, copie o código gerado, cole no app do seu banco na opção Pix, confira os detalhes e finalize. Ambas as opções são rápidas, seguras e atualizam o pedido automaticamente. ." ,
                fontSize = 13.sp,
                fontWeight = FontWeight(340),
                color = Color(0xFF30323D),
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Simple
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            FloatingActionButton(
                onClick = { },
                shape = RoundedCornerShape(5.dp),
                containerColor = Color(232, 197, 71),
                modifier = Modifier
                    .padding(bottom =  8.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Comprar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
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
            Pix( navController, modifier = Modifier.padding(innerPadding))
        }
    }
}
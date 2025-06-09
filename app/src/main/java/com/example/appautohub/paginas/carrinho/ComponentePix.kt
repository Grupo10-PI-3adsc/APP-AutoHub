package com.example.appautohub.paginas.carrinho

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appautohub.data.state.PixUiState
import com.example.appautohub.data.state.StatusUiState
import com.example.appautohub.data.viewmodel.CarrinhoViewModel
import com.example.appautohub.data.viewmodel.ViewModelPagamento
import com.example.appautohub.ui.theme.components.QRCodeGenerator
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun ComponentePix(
    pagamentoViewModel: ViewModelPagamento,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val viewModelcarrinho = koinInject<CarrinhoViewModel>()

    val pixState = pagamentoViewModel.pixUiState.collectAsState().value
    val statusUiState by pagamentoViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val pollingAtivo = remember { mutableStateOf(false) }

    // Observa o status do pagamento e navega quando for concluído
    LaunchedEffect(statusUiState) {
        val statusSuccess = statusUiState as? StatusUiState.Success
        if (statusSuccess != null && statusSuccess.status == "CONCLUIDA") {

            viewModelcarrinho.limparCarrinho()


            delay(5000)


            navController.navigate("produtos") {
                popUpTo("componentePix") { inclusive = true }
            }
            pagamentoViewModel.resetStatus() // Evita loop
            pollingAtivo.value = false       // Libera para próximo pagamento

        }
    }

    // Polling periódico após sucesso na geração do Pix
    LaunchedEffect(pixState) {
        if (pixState is PixUiState.Success && !pollingAtivo.value) {
            pollingAtivo.value = true
            val txid = pagamentoViewModel.ultimoTxId
            try {
                if (txid != null) {
                    while (true) {
                        pagamentoViewModel.consultarStatus(txid)
                        val statusAtual = pagamentoViewModel.uiState.value
                        if (statusAtual is StatusUiState.Success && statusAtual.status == "CONCLUIDA") {
                            break
                        }
                        delay(5000) // espera 5 segundos
                    }
                }
            } finally {
                pollingAtivo.value = false
            }
        }
    }


    // Exibe um AlertDialog quando pagamento for concluído
    val statusSuccess = statusUiState as? StatusUiState.Success
    if (statusSuccess != null && statusSuccess.status == "CONCLUIDA") {

        AlertDialog(
            onDismissRequest = {},
            confirmButton = {},
            title = { Text("Pagamento Concluído") },
            text = { Text("Pagamento realizado com sucesso!") }
        )
    }

    // UI principal
    when (pixState) {
        is PixUiState.Loading -> {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text("Gerando QR Code...", Modifier.padding(top = 8.dp))
            }
        }

        is PixUiState.Success -> {
            val code = pixState.response.pixCopiaECola
            val qrBitmap by remember(code) {
                mutableStateOf(QRCodeGenerator.encode(code, size = 400))
            }

            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Escaneie ou copie abaixo", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(16.dp))

                Image(
                    bitmap = qrBitmap.asImageBitmap(),
                    contentDescription = "QR Code Pix",
                    modifier = Modifier.size(200.dp)
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = code,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(12.dp)
                        .fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Button(onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("Pix Copia e Cola", code)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(context, "Pix copiado!", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Copiar código Pix")
                }

                Spacer(Modifier.height(16.dp))

                when (statusUiState) {
                    is StatusUiState.Success -> {
                        val status = (statusUiState as? StatusUiState.Success)?.status ?: "Desconhecido"
                        Text("Status do pagamento: $status", color = Color.Green)
                    }

                    is StatusUiState.Loading -> {
                        CircularProgressIndicator()
                        Text("Verificando pagamento...")
                    }

                    is StatusUiState.Error -> {
                        val message = (statusUiState as? StatusUiState.Error)?.message ?: "Erro desconhecido"
                        Text("Erro ao verificar status: $message", color = Color.Red)
                    }

                    StatusUiState.Idle -> {}
                }
            }
        }

        is PixUiState.Error -> {
            Text(
                text = "Erro ao gerar Pix: ${pixState.message}",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        PixUiState.Idle -> {
            Text("Clique em pagar para gerar o Pix", modifier = Modifier.padding(16.dp))
        }
    }
}


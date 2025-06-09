package com.example.appautohub.paginas.carrinho

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.appautohub.data.state.PixUiState
import com.example.appautohub.data.viewmodel.ViewModelPagamento
import androidx.compose.runtime.collectAsState
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import com.example.appautohub.ui.theme.components.QRCodeGenerator

@Composable
fun ComponentePix(
    pagamentoViewModel: ViewModelPagamento,
    modifier: Modifier = Modifier
) {
    val pixState = pagamentoViewModel.pixUiState.collectAsState().value
    val context = LocalContext.current

    when (pixState) {
        is PixUiState.Loading -> {
            Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text("Gerando QR Code...", Modifier.padding(top = 8.dp))
            }
        }
        is PixUiState.Success -> {
            val code = pixState.response.pixCopiaECola
            // gera bitmap do QR apenas uma vez para cada código
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
            // Opcional: nada a mostrar ainda, ou um placeholder
            Text("Clique em pagar para gerar o Pix", modifier = Modifier.padding(16.dp))
        }
    }
}

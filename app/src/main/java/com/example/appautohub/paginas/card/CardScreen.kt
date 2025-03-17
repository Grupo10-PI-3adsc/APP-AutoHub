package com.example.appautohub.paginas.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appautohub.R
import com.example.appautohub.paginas.payment.PaymentScreen
import com.example.appautohub.ui.theme.AppAutoHubTheme
import com.example.appautohub.ui.theme.components.HeaderApp
import com.example.appautohub.ui.theme.components.HeaderTitle

@Composable
fun CardScreen() {
    var text by remember { mutableStateOf("") }

    var expandedMonth by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf("01 - janeiro") }

    var expandedYear by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf("2024") }

    var cvv by remember { mutableStateOf("") }

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

                HeaderTitle("ADICIONAR CARTÃO")

                Spacer(modifier = Modifier.height(50.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.credit_card),
                        contentDescription = "Cartão icone",
                        Modifier.size(30.dp)
                    )

                    Text(
                        "Insira as Informações:",
                        Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Nome do cartão")
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Nome") },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(2f)) {
                        Text("Número do cartão")
                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Número") },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("CVV", fontSize = 14.sp)
                        OutlinedTextField(
                            value = cvv,
                            onValueChange = { cvv = it },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier.fillMaxWidth().padding(top = 7.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(2f)) {
                        Text("Data de validade", fontSize = 14.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(modifier = Modifier.weight(1f)) {
                                OutlinedButton(
                                    onClick = { expandedMonth = true },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(5.dp),
                                    border = BorderStroke(1.dp, Color.Gray),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                                ) {
                                    Text(selectedMonth, modifier = Modifier.weight(1f))
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                                }
                                DropdownMenu(
                                    expanded = expandedMonth,
                                    onDismissRequest = { expandedMonth = false }
                                ) {
                                    listOf("01 - janeiro", "02 - fevereiro", "03 - março").forEach { month ->
                                        DropdownMenuItem(text = { Text(month) }, onClick = {
                                            selectedMonth = month
                                            expandedMonth = false
                                        })
                                    }
                                }
                            }

                            Box(modifier = Modifier.weight(1f)) {
                                OutlinedButton(
                                    onClick = { expandedYear = true },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(5.dp),
                                    border = BorderStroke(1.dp, Color.Gray),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                                ) {
                                    Text(selectedYear, modifier = Modifier.weight(1f))
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                                }
                                DropdownMenu(
                                    expanded = expandedYear,
                                    onDismissRequest = { expandedYear = false }
                                ) {
                                    listOf("2024", "2025", "2026").forEach { year ->
                                        DropdownMenuItem(text = { Text(year) }, onClick = {
                                            selectedYear = year
                                            expandedYear = false
                                        })
                                    }
                                }
                            }
                        }
                    }
                }

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
       CardScreen()
    }
}
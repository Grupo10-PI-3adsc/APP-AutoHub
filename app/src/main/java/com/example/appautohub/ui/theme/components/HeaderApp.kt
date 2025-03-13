package com.example.appautohub.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appautohub.R

@Composable
fun HeaderApp() {
    Row(
        modifier = Modifier.run {
            fillMaxWidth()
                .height(80.dp)
                .background(Color.DarkGray)
                .padding(0.dp)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 20.dp),
            painter = painterResource(id = R.drawable.lotus),
            contentDescription = "Logo lotus"
        )
    }
}

@Composable
fun HeaderTitle(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }
        Text(
            title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )
    }
}
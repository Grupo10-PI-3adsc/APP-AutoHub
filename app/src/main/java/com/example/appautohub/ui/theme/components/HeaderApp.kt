package com.example.appautohub.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
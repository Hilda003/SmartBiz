package com.example.smartbiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun IncomeComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        // Centered Icon
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun IncomeComponentPreview() {
    IncomeComponent()
}
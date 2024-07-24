package com.lhfp.studifydemo.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun SettingsButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Icon(
            Icons.Default.Settings,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .padding(end = 15.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    StudifyDemoTheme {
        SettingsButton()
    }
}
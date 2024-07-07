package com.lhfp.studifydemo.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "HomeScreen",
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
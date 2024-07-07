package com.lhfp.studifydemo.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(5.dp)
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "Search bar icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    StudifyDemoTheme {
        SearchBar()
    }
}
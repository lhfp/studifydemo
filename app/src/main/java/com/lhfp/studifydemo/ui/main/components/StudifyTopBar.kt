package com.lhfp.studifydemo.ui.main.components

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudifyTopBar(
    logo: @Composable () -> Unit,
    rightSide: @Composable () -> Unit,
) {
    TopAppBar(
        title = { logo() },
        actions = { rightSide() }
    )
}

@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LightMode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun StudifyTopBarPreview() {
    StudifyDemoTheme {
        StudifyTopBar(logo = { Logo() }, rightSide = { SettingsButton() })
    }
}
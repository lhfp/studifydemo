package com.lhfp.studifydemo.ui.main.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhfp.studifydemo.BuildConfig
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import com.lhfp.studifydemo.ui.theme.robotoFont

@Composable
fun StudifyTopBar(
    logo: @Composable () -> Unit,
    rightSide: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            logo()
            rightSide()
        }
        VersionText(modifier = Modifier.align(Alignment.BottomStart))
    }
}

@Composable
fun VersionText(modifier: Modifier) {
    Text(
        text = "studify-${BuildConfig.VERSION_NAME}",
        color = Color.Cyan,
        fontFamily = robotoFont,
        fontSize = 8.sp,
        modifier = modifier.height(17.dp)
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
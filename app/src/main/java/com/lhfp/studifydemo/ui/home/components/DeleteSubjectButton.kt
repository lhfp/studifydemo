package com.lhfp.studifydemo.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.R

@Composable
fun DeleteSubjectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.red_400)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .size(60.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp)
            )
        }
    }
}

@Preview
@Composable
private fun DeleteSubjectButtonPreview() {
    DeleteSubjectButton({})
}
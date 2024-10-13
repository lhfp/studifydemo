package com.lhfp.studifydemo.ui.notes_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListTopBar(
    subjectName: String,
    subjectColor: Color,
    navigateBack: () -> Unit,
    action: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = subjectName,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        actions = { action() },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = subjectColor)
    )
}
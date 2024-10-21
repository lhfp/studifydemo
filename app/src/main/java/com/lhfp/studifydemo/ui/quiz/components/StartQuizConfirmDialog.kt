package com.lhfp.studifydemo.ui.quiz.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun StartQuizConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = "Yes",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.secondary)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "No",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.error)
                )
            }
        },
        title = {
            Text(
                text = "Start quiz?",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = "Your quiz contains questions up to notes from {INSERT DAY HERE}."
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Quiz,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        tonalElevation = 10.dp
    )
}

@Preview
@Composable
private fun StartQuizConfirmDialogPreview() {
    StudifyDemoTheme {
        StartQuizConfirmDialog(
            onDismiss = {},
            onConfirm = {}
        )
    }
}
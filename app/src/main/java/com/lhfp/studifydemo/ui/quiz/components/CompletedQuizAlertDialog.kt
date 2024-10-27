package com.lhfp.studifydemo.ui.quiz.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun CompletedQuizAlertDialog(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.secondary)
                )
            }
        },
        title = {
            Text(text = "Completed Quiz")
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.CheckCircleOutline,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
        },
        text = {
            Text(text = "You have already completed this quiz.")
        },
    )
}

@Preview
@Composable
private fun CompletedQuizAlertDialogPreview() {
    StudifyDemoTheme {
        CompletedQuizAlertDialog(onDismiss = {})
    }
}
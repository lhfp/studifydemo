package com.lhfp.studifydemo.ui.quiz.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun QuestionAnswerResult(
    isCorrect: Boolean,
    correctAnswer: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = if (isCorrect)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.error
        ),
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                imageVector = if (isCorrect) Icons.Filled.Check else Icons.Filled.Cancel,
                contentDescription = "",
                modifier = Modifier.padding(end = 10.dp),
            )
            Text(
                text = if (isCorrect) "Well done, correct!" else "The correct answer was $correctAnswer",
                color = MaterialTheme.colorScheme.onPrimary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuestionAnswerCorrectResultPreview() {
    StudifyDemoTheme {
        QuestionAnswerResult(isCorrect = true, correctAnswer = "Correct Answer")
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuestionAnswerWrongResultPreview() {
    StudifyDemoTheme {
        QuestionAnswerResult(isCorrect = false, correctAnswer = "")
    }
}
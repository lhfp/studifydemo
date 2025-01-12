package com.lhfp.studifydemo.ui.quiz.quiz_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun QuizItemView(
    quizWithQuestions: QuizWithQuestions,
    subject: Subject,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = colorResource(id = subject.color)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = quizWithQuestions.quiz.isCompleted,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Completed",
                        style = TextStyle(color = Color.White)
                    )
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }

            Row {
                Icon(
                    imageVector = Icons.Filled.Quiz,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(
                    text = quizWithQuestions.quiz.name,
                    style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuizItemPreview() {
    StudifyDemoTheme {
        with(MockDataSources) {
            QuizItemView(
                quizWithQuestions = QUIZ_WITH_QUESTIONS_LIST[0],
                subject = ENGLISH_SUBJECT
            ) {}
        }
    }
}

@Preview
@Composable
private fun CompletedQuizItemPreview() {
    StudifyDemoTheme {
        with(MockDataSources) {
            QuizItemView(
                quizWithQuestions = QUIZ_WITH_QUESTIONS_LIST[0].copy(
                    quiz = QUIZ_WITH_QUESTIONS_LIST[0].quiz.copy(isCompleted = true)
                ),
                subject = ENGLISH_SUBJECT
            ) {}
        }
    }
}
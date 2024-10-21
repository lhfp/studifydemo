package com.lhfp.studifydemo.ui.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun QuizScreen(
    quizId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = hiltViewModel()
) {
    StudifyDemoTheme {
        val currentQuiz = quizViewModel.quizListState.value.quizzes.find { it.quiz.quizId == quizId }
        currentQuiz?.let {
            QuizScreenContent(quiz = it)
        }
    }
}

@Composable
fun QuizScreenContent(
    quiz: QuizWithQuestions
) {
    Scaffold { padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(10.dp)) {
            items(items = quiz.questions) {
                Column(modifier = Modifier.padding(vertical = 10.dp)) {
                    Text(
                        text = it.question.text,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    it.options.forEach { option ->
                        Text(
                            text = "- " + option.answer,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenContentPreview() {
    StudifyDemoTheme {
        QuizScreenContent(
            quiz = MockDataSources.QUIZ_WITH_QUESTIONS_LIST[0]
        )
    }
}
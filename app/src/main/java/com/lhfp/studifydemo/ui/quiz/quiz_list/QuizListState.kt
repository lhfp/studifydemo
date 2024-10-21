package com.lhfp.studifydemo.ui.quiz.quiz_list

import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.ui.quiz.UIState

data class QuizListState(
    val quizzes: List<QuizWithQuestions> = emptyList(),
    var uiState: UIState = UIState.Initial
)
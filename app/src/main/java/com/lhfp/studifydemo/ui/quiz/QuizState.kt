package com.lhfp.studifydemo.ui.quiz

import com.lhfp.studifydemo.domain.model.QuizWithQuestions

data class QuizState(
    val quizzes: List<QuizWithQuestions> = emptyList(),
    var uiState: UIState = UIState.Initial
)

sealed interface UIState {
    data object Initial : UIState
    data object Loading : UIState
    data class Success(val quizJson: String) : UIState
    data class Error(val message: String) : UIState
}
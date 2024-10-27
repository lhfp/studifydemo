package com.lhfp.studifydemo.ui.quiz

import com.lhfp.studifydemo.domain.model.QuizWithQuestions

data class QuizState(
    val currentQuiz: QuizWithQuestions? = null,
    val currentQuestionIndex: Int = 0,
    var uiState: UIState = UIState.Initial
)

sealed interface UIState {
    data object Initial : UIState
    data object Loading : UIState
    data class Success(val quizJson: String) : UIState
    data class OnQuizReady(val newQuizId: Int) : UIState
    data class Error(val message: String?) : UIState
}
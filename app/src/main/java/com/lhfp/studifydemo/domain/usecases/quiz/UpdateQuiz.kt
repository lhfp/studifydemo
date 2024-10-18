package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.repository.QuizRepository

class UpdateQuiz(private val repository: QuizRepository) {
    suspend operator fun invoke(quiz: Quiz) {
        repository.updateQuiz(quiz)
    }
}
package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.repository.QuizRepository

class GetQuizWithQuestions(private val repository: QuizRepository) {
    suspend operator fun invoke(quizId: Int): QuizWithQuestions? {
        return repository.getQuizWithQuestions(quizId)
    }
}
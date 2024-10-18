package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.repository.QuizRepository

class GetQuizById(private val repository: QuizRepository) {
    suspend operator fun invoke(quizId: Int): Quiz? {
        return repository.getQuizById(quizId)
    }
}
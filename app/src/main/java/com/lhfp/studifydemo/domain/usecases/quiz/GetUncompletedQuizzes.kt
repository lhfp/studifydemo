package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class GetUncompletedQuizzes(private val repository: QuizRepository) {
    suspend operator fun invoke(): Flow<List<Quiz>> {
        return repository.getUncompletedQuizzes()
    }
}
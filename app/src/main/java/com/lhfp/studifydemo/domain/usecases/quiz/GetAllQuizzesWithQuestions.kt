package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class GetAllQuizzesWithQuestions(private val repository: QuizRepository) {
    operator fun invoke(): Flow<List<QuizWithQuestions>> {
        return repository.getAllQuizzesWithQuestions()
    }
}
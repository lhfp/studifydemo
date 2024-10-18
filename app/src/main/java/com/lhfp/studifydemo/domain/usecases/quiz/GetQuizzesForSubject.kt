package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.repository.QuizRepository

class GetQuizzesForSubject(private val repository: QuizRepository) {
    operator fun invoke(subjectId: Int) = repository.getQuizzesForSubject(subjectId)
}
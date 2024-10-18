package com.lhfp.studifydemo.domain.repository

import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun insertQuiz(quiz: Quiz): Long

    fun getQuizzesForSubject(subjectId: Int): Flow<List<Quiz>>

    suspend fun getQuizById(quizId: Int): Quiz?

    fun getAllQuizzes(): Flow<List<Quiz>>

    fun getUncompletedQuizzes(): Flow<List<Quiz>>

    fun getCompletedQuizzes(): Flow<List<Quiz>>

    suspend fun deleteQuiz(quiz: Quiz)

    suspend fun updateQuiz(quiz: Quiz)

    fun getQuizWithQuestions(quizId: Int): QuizWithQuestions?

    suspend fun addQuizWithQuestionsWithOptions(
        quiz: Quiz,
        questions: List<QuestionWithOptions>,
    ): Long

    fun getAllQuizzesWithQuestions(): Flow<List<QuizWithQuestions>>
}
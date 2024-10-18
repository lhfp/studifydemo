package com.lhfp.studifydemo.data.repository

import com.lhfp.studifydemo.data.db.dao.QuizDao
import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class QuizRepositoryImpl(
    private val quizDao: QuizDao
) : QuizRepository {
    override suspend fun insertQuiz(quiz: Quiz): Long {
        return quizDao.insertQuiz(quiz)
    }

    override fun getQuizzesForSubject(subjectId: Int): Flow<List<Quiz>> {
        return quizDao.getQuizzesForSubject(subjectId)
    }

    override suspend fun getQuizById(quizId: Int): Quiz? {
        return quizDao.getQuizById(quizId)
    }

    override fun getAllQuizzes(): Flow<List<Quiz>> {
        return quizDao.getAllQuizzes()
    }

    override fun getUncompletedQuizzes(): Flow<List<Quiz>> {
        return quizDao.getUncompletedQuizzes()
    }

    override fun getCompletedQuizzes(): Flow<List<Quiz>> {
        return quizDao.getCompletedQuizzes()
    }

    override suspend fun deleteQuiz(quiz: Quiz) {
        quizDao.deleteQuiz(quiz)
    }

    override suspend fun updateQuiz(quiz: Quiz) {
        quizDao.updateQuiz(quiz)
    }

    override fun getQuizWithQuestions(quizId: Int): QuizWithQuestions? {
        return quizDao.getQuizWithQuestions(quizId)
    }

    override suspend fun addQuizWithQuestionsWithOptions(
        quiz: Quiz,
        questions: List<QuestionWithOptions>
    ): Long {
        return quizDao.addQuizWithQuestionsWithOptions(quiz, questions)
    }

    override fun getAllQuizzesWithQuestions(): Flow<List<QuizWithQuestions>> {
        return quizDao.getAllQuizzesWithQuestions()
    }
}
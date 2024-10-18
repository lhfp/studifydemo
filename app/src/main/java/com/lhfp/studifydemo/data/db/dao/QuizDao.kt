package com.lhfp.studifydemo.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lhfp.studifydemo.domain.model.Option
import com.lhfp.studifydemo.domain.model.Question
import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: Quiz): Long

    @Transaction
    @Query("SELECT * FROM quiz WHERE subjectId = :subjectId")
    fun getQuizzesForSubject(subjectId: Int): Flow<List<Quiz>>

    @Transaction
    @Query("SELECT * FROM quiz WHERE quizId = :quizId")
    suspend fun getQuizById(quizId: Int): Quiz?

    @Query("SELECT * FROM quiz")
    fun getAllQuizzes(): Flow<List<Quiz>>

    @Query("SELECT * FROM quiz WHERE isCompleted = 0")
    fun getUncompletedQuizzes(): Flow<List<Quiz>>

    @Query("SELECT * FROM quiz WHERE isCompleted = 1")
    fun getCompletedQuizzes(): Flow<List<Quiz>>

    @Delete
    suspend fun deleteQuiz(quiz: Quiz)

    @Update
    suspend fun updateQuiz(quiz: Quiz)

    @Query("SELECT * FROM quiz WHERE quizId = :quizId")
    fun getQuizWithQuestions(quizId: Int): QuizWithQuestions?

    @Transaction
    @Query("SELECT * FROM quiz")
    fun getAllQuizzesWithQuestions(): Flow<List<QuizWithQuestions>>

    @Transaction
    suspend fun addQuizWithQuestionsWithOptions(
        quiz: Quiz,
        questions: List<QuestionWithOptions>,
    ): Long {
        val quizId = insertQuiz(quiz)

        questions.forEach {
            addQuestionWithOptions(
                Question(
                    quizOwnerId = quizId.toInt(),
                    text = it.question.text,
                    questionType = it.question.questionType,
                    selectedOptionId = null
                ), it.options
            )
        }

        return quizId
    }

    @Transaction
    suspend fun addQuestionWithOptions(
        question: Question,
        options: List<Option>
    ) {
        val questionId = insertQuestion(question)
        options.forEach {
            insertOption(
                Option(
                    questionOwnerId = questionId.toInt(),
                    answer = it.answer,
                    isCorrect = it.isCorrect
                )
            )
        }
    }



    // Question Dao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question): Long

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<Question>): Array<Long>


    // Option Dao

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: Option): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOptions(options: List<Option>): Array<Long>

    @Delete
    suspend fun deleteOption(option: Option)

    @Update
    suspend fun updateOption(option: Option)

}
package com.lhfp.studifydemo.domain.usecases.quiz

import com.google.gson.Gson
import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizType
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.repository.QuizRepository

class CreateQuiz(
    private val quizRepository: QuizRepository
) {
    suspend operator fun invoke(generatedQuiz: String, subjectId: Int): Long {
        val gson = Gson()
        gson.fromJson(generatedQuiz, GeneratedQuizFromAI::class.java).apply {
            val quizFromGenerativeModel = QuizWithQuestions(
                quiz = Quiz(
                    name = this.name,
                    subjectId = subjectId,
                    type = QuizType.MULTIPLE_CHOICE.ordinal,
                    numQuestions = this.questions.size,
                    numCorrectAnswers = 0
                ),
                questions = this.questions
            )

            return quizRepository.addQuizWithQuestionsWithOptions(
                quiz = quizFromGenerativeModel.quiz,
                questions = quizFromGenerativeModel.questions
            )
        }
    }
}

data class GeneratedQuizFromAI(
    val name: String,
    val questions: List<QuestionWithOptions>
)
package com.lhfp.studifydemo.domain.usecases.quiz

import com.lhfp.studifydemo.domain.model.Option
import com.lhfp.studifydemo.domain.model.Question
import com.lhfp.studifydemo.domain.model.QuestionType
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizType
import com.lhfp.studifydemo.domain.repository.QuizRepository

class CreateQuiz(
    private val quizRepository: QuizRepository
) {
    suspend operator fun invoke(generatedQuiz: String, subjectId: Int): Long {

        return 1
        //return quizRepository.addQuizWithQuestionsWithOptions()
    }
}
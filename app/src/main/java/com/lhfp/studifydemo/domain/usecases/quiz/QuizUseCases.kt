package com.lhfp.studifydemo.domain.usecases.quiz

data class QuizUseCases(
    val createQuiz: CreateQuiz,
    val getQuizzesForSubject: GetQuizzesForSubject,
    val getQuizById: GetQuizById,
    val getAllQuizzes: GetAllQuizzes,
    val getUncompletedQuizzes: GetUncompletedQuizzes,
    val getCompletedQuizzes: GetCompletedQuizzes,
    val deleteQuiz: DeleteQuiz,
    val updateQuiz: UpdateQuiz,
    val getQuizWithQuestions: GetQuizWithQuestions,
    val getAllQuizzesWithQuestions: GetAllQuizzesWithQuestions
)
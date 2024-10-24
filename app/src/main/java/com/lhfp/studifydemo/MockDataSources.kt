package com.lhfp.studifydemo

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Option
import com.lhfp.studifydemo.domain.model.Question
import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.model.SubjectWithNotes

class MockDataSources {

    companion object {
        private val GEOGRAPHY_SUBJECT = Subject(
            subjectId = 0,
            name = "Geography",
            color = R.color.purple_300,
            updatedAt = System.currentTimeMillis()
        )

        private val HISTORY_SUBJECT = Subject(
            subjectId = 1,
            name = "History",
            color = R.color.bluegray_400,
            updatedAt = System.currentTimeMillis()
        )

        val ENGLISH_SUBJECT = Subject(
            subjectId = 2,
            name = "English",
            color = R.color.brown_400,
            updatedAt = System.currentTimeMillis()
        )

        private val PORTUGUESE_SUBJECT = Subject(
            subjectId = 3,
            name = "Portuguese",
            color = R.color.lightgreen_400,
            updatedAt = System.currentTimeMillis()
        )

        private val PHYSICS_SUBJECT = Subject(
            subjectId = 4,
            name = "Physics",
            color = R.color.pink_400,
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_1 = Note(
            subjectId = 1,
            title = "Note title 1",
            content =  LoremIpsum().values.last(),
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_2 = Note(
            subjectId = 1,
            title = "Note title 2",
            content = "",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_3 = Note(
            subjectId = 2,
            title = "Note title 3",
            content = "",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        val NOTES_LIST = listOf(
            MOCK_NOTE_1,
            MOCK_NOTE_2,
            MOCK_NOTE_3,
        )

        val SUBJECT_WITH_NOTES_LIST = listOf(
            SubjectWithNotes(
                subject = GEOGRAPHY_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = HISTORY_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = PORTUGUESE_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = PHYSICS_SUBJECT,
                notes = NOTES_LIST
            ),
        )

        private val QUIZ_1 = Quiz(
            quizId = 1,
            name = "Quiz 1",
            subjectId = 1,
            isCompleted = false,
            completedAt = null,
            numQuestions = 10,
            numCorrectAnswers = 0,
            type = 1
        )

        private val QUIZ_2 = Quiz(
            quizId = 2,
            name = "Quiz 2",
            subjectId = 4,
            isCompleted = false,
            completedAt = null,
            numQuestions = 10,
            numCorrectAnswers = 0,
            type = 1
        )

        private val QUIZ_3 = Quiz(
            quizId = 3,
            name = "Quiz 3",
            subjectId = 0,
            isCompleted = false,
            completedAt = null,
            numQuestions = 10,
            numCorrectAnswers = 0,
            type = 1
        )

        private val QUIZ_1_QUESTIONS = listOf(
            QuestionWithOptions(
                question = Question(
                    text = LoremIpsum(15).values.last(),
                    quizOwnerId = 1,
                    questionType = 1
                ),
                options = listOf(
                    Option(answer = LoremIpsum(15).values.last(), isCorrect = true, questionOwnerId = 1),
                    Option(answer = "Option 2", isCorrect = false, questionOwnerId = 1),
                    Option(answer = LoremIpsum(15).values.last(), isCorrect = false, questionOwnerId = 1),
                    Option(answer = LoremIpsum(15).values.last(), isCorrect = false, questionOwnerId = 1)
                )
            ),
            QuestionWithOptions(
                question = Question(
                    text = "Question 2",
                    quizOwnerId = 1,
                    questionType = 1
                ),
                options = listOf(
                    Option(answer = "Option 1", isCorrect = false, questionOwnerId = 1),
                    Option(answer = "Option 2", isCorrect = true, questionOwnerId = 1)
                )
            ),
        )

        val QUIZ_WITH_QUESTIONS_LIST =  listOf(
            QuizWithQuestions(
                quiz = QUIZ_1,
                questions = QUIZ_1_QUESTIONS
            ),
            QuizWithQuestions(
                quiz = QUIZ_2,
                questions = QUIZ_1_QUESTIONS
            ),
            QuizWithQuestions(
                quiz = QUIZ_3.copy(isCompleted = true),
                questions = QUIZ_1_QUESTIONS
            )
        )
    }
}
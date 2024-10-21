package com.lhfp.studifydemo.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = ["subjectId"],
        childColumns = ["subjectId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val quizId: Int = 0,
    val subjectId: Int,
    val name: String,
    val type: Int,
    val numQuestions: Int,
    val numCorrectAnswers: Int,
    val completedAt: Long? = null,
    val startedAt: Long? = null,
    val dateLimit: Long? = null,
    val isCompleted: Boolean = false
)


data class QuizWithQuestions(
    @Embedded
    val quiz: Quiz,
    @Relation(
        entity = Question::class,
        parentColumn = "quizId",
        entityColumn = "quizOwnerId")
    val questions: List<QuestionWithOptions>
)

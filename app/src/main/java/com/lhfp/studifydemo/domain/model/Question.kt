package com.lhfp.studifydemo.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["quizId"],
        childColumns = ["quizOwnerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val questionId: Int = 0,
    val quizOwnerId: Int,
    val text: String,
    val questionType: Int,
    val selectedOptionId: Int? = null
)

data class QuestionWithOptions(
    @Embedded val question: Question,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionOwnerId"
    ) val options: List<Option>
)
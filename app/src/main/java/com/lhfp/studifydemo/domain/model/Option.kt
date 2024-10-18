package com.lhfp.studifydemo.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Question::class,
        parentColumns = ["questionId"],
        childColumns = ["questionOwnerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Option(
    @PrimaryKey(autoGenerate = true)
    val optionId: Int = 0,
    val questionOwnerId: Int,
    val answer: String,
    val isCorrect: Boolean
)
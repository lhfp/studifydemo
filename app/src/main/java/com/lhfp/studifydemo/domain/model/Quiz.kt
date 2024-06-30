package com.lhfp.studifydemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val quizId: Int,
    val name: String,
    val type: Int,
    val numQuestions: Int,
    val numCorrectAnswers: Int,
    val completedAt: Long,
    val startedAt: Long,
    val dateLimit: Long,
    val isCompleted: Boolean
)

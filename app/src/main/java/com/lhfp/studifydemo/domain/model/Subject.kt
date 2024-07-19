package com.lhfp.studifydemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int = 0,
    val name: String,
    val color: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long
)

class InvalidSubjectException(message: String): Exception(message)

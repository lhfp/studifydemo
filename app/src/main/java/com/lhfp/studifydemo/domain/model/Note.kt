package com.lhfp.studifydemo.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Subject::class,
        parentColumns = ["subjectId"],
        childColumns = ["subjectId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val subjectId: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)

package com.lhfp.studifydemo.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectWithNotes(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subjectId"
    ) val notes: List<Note>
)

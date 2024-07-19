package com.lhfp.studifydemo.domain.usecases.subjects

data class SubjectsUseCases(
    val getSubjects: GetSubjects,
    val addSubject: AddSubject,
    val removeSubject: RemoveSubject,
    val getSubjectsWithNotes: GetSubjectsWithNotes
)
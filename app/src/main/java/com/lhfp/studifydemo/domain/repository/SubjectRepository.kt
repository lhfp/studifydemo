package com.lhfp.studifydemo.domain.repository

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {

    suspend fun insertSubject(subject: Subject)

    suspend fun deleteSubject(subject: Subject)

    suspend fun updateSubject(subject: Subject)

    suspend fun getSubjectById(subjectId: Int): Subject

    fun getAllSubjects(): Flow<List<Subject>>

    suspend fun getSubjectWithNotes(subjectId: Int): List<SubjectWithNotes>

    suspend fun updateSubjectWithNotes(subjectWithNotes: SubjectWithNotes)

    suspend fun insertNotes(notes: List<Note>)

    suspend fun deleteNote(note: Note)
}
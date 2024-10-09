package com.lhfp.studifydemo.domain.repository

import com.lhfp.studifydemo.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    fun getNotesForSubject(subjectId: Int): Flow<List<Note>>

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}
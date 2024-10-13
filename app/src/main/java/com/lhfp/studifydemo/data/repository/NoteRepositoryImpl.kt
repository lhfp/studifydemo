package com.lhfp.studifydemo.data.repository

import com.lhfp.studifydemo.data.db.dao.NoteDao
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override suspend fun insertNote(note: Note): Long {
        return dao.insertNote(note)
    }

    override fun getNotesForSubject(subjectId: Int): Flow<List<Note>> {
        return dao.getNotesForSubject(subjectId)
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

}
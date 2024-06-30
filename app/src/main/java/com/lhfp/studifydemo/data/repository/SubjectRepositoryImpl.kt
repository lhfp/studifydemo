package com.lhfp.studifydemo.data.repository

import androidx.room.Transaction
import com.lhfp.studifydemo.data.db.dao.SubjectDao
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow

class SubjectRepositoryImpl(
    private val dao: SubjectDao
) : SubjectRepository {

    override suspend fun insertSubject(subject: Subject) {
        dao.insertSubject(subject)
    }

    override suspend fun deleteSubject(subject: Subject) {
        dao.deleteSubject(subject)
    }

    override suspend fun getSubjectById(subjectId: Int): Subject {
        return dao.getSubjectById(subjectId)
    }

    override fun getAllSubjects(): Flow<List<Subject>> {
        return dao.getAllSubjects()
    }

    override suspend fun getSubjectWithNotes(subjectId: Int): List<SubjectWithNotes> {
        return dao.getSubjectWithNotes(subjectId)
    }

    override suspend fun updateSubject(subject: Subject) {
        dao.updateSubject(subject)
    }

    @Transaction
    override suspend fun updateSubjectWithNotes(subjectWithNotes: SubjectWithNotes) {
        dao.updateSubject(subjectWithNotes.subject)

        dao.insertNotes(subjectWithNotes.notes)
    }

    override suspend fun insertNotes(notes: List<Note>) {
        dao.insertNotes(notes)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
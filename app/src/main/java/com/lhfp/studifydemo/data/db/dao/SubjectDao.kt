package com.lhfp.studifydemo.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Query("SELECT * FROM subject WHERE subjectId = :id")
    suspend fun getSubjectById(id: Int) : Subject

    @Query("SELECT * FROM subject")
    fun getAllSubjects(): Flow<List<Subject>>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectId = :subjectId")
    suspend fun getSubjectWithNotes(subjectId: Int): List<SubjectWithNotes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: List<Note>)

    @Update
    suspend fun updateNotes(notes: List<Note>)

    @Delete
    suspend fun deleteNote(note: Note)
}
package com.lhfp.studifydemo.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lhfp.studifydemo.data.db.dao.NoteDao
import com.lhfp.studifydemo.data.db.dao.QuizDao
import com.lhfp.studifydemo.data.db.dao.SubjectDao
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Option
import com.lhfp.studifydemo.domain.model.Question
import com.lhfp.studifydemo.domain.model.Quiz
import com.lhfp.studifydemo.domain.model.Subject

@Database(
    entities = [Note::class, Subject::class, Quiz::class, Question::class, Option::class],
    version = 1
)
abstract class StudifyDatabase: RoomDatabase() {

    abstract val subjectDao: SubjectDao
    abstract val noteDao: NoteDao
    abstract val quizDao: QuizDao

    companion object {
        const val DATABASE_NAME = "studify_db"
    }
}
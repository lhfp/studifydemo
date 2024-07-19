package com.lhfp.studifydemo.di

import android.app.Application
import androidx.room.Room
import com.lhfp.studifydemo.data.db.database.StudifyDatabase
import com.lhfp.studifydemo.data.repository.NoteRepositoryImpl
import com.lhfp.studifydemo.data.repository.SubjectRepositoryImpl
import com.lhfp.studifydemo.domain.repository.NoteRepository
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import com.lhfp.studifydemo.domain.usecases.subjects.AddSubject
import com.lhfp.studifydemo.domain.usecases.subjects.GetSubjects
import com.lhfp.studifydemo.domain.usecases.subjects.GetSubjectsWithNotes
import com.lhfp.studifydemo.domain.usecases.subjects.RemoveSubject
import com.lhfp.studifydemo.domain.usecases.subjects.SubjectsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStudifyDatabase(app: Application): StudifyDatabase {
        return Room.databaseBuilder(
            app,
            StudifyDatabase::class.java,
            StudifyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSubjectRepository(db: StudifyDatabase): SubjectRepository {
        return SubjectRepositoryImpl(db.subjectDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: StudifyDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideSubjectsUseCase(repository: SubjectRepository): SubjectsUseCases {
        return SubjectsUseCases(
            getSubjects = GetSubjects(repository),
            addSubject = AddSubject(repository),
            removeSubject = RemoveSubject(repository),
            getSubjectsWithNotes = GetSubjectsWithNotes(repository)
        )
    }
}
package com.lhfp.studifydemo.di

import android.app.Application
import androidx.room.Room
import com.lhfp.studifydemo.data.db.database.StudifyDatabase
import com.lhfp.studifydemo.data.repository.NoteRepositoryImpl
import com.lhfp.studifydemo.data.repository.QuizRepositoryImpl
import com.lhfp.studifydemo.data.repository.SubjectRepositoryImpl
import com.lhfp.studifydemo.domain.repository.NoteRepository
import com.lhfp.studifydemo.domain.repository.QuizRepository
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import com.lhfp.studifydemo.domain.usecases.notes.AddNote
import com.lhfp.studifydemo.domain.usecases.notes.DeleteNote
import com.lhfp.studifydemo.domain.usecases.notes.GetNotes
import com.lhfp.studifydemo.domain.usecases.notes.NotesUseCases
import com.lhfp.studifydemo.domain.usecases.notes.UpdateNote
import com.lhfp.studifydemo.domain.usecases.quiz.CreateQuiz
import com.lhfp.studifydemo.domain.usecases.quiz.DeleteQuiz
import com.lhfp.studifydemo.domain.usecases.quiz.GetAllQuizzes
import com.lhfp.studifydemo.domain.usecases.quiz.GetAllQuizzesWithQuestions
import com.lhfp.studifydemo.domain.usecases.quiz.GetCompletedQuizzes
import com.lhfp.studifydemo.domain.usecases.quiz.GetQuizById
import com.lhfp.studifydemo.domain.usecases.quiz.GetQuizWithQuestions
import com.lhfp.studifydemo.domain.usecases.quiz.GetQuizzesForSubject
import com.lhfp.studifydemo.domain.usecases.quiz.GetUncompletedQuizzes
import com.lhfp.studifydemo.domain.usecases.quiz.QuizUseCases
import com.lhfp.studifydemo.domain.usecases.quiz.UpdateQuiz
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
    fun provideQuizRepository(db: StudifyDatabase): QuizRepository {
        return QuizRepositoryImpl(db.quizDao)
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

    @Provides
    @Singleton
    fun provideNotesUseCase(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotes(repository),
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            updateNote = UpdateNote(repository)
        )
    }

    @Provides
    @Singleton
    fun provideQuizUseCases(
        quizRepository: QuizRepository,
    ): QuizUseCases {
        return QuizUseCases(
            createQuiz = CreateQuiz(quizRepository),
            getQuizzesForSubject = GetQuizzesForSubject(quizRepository),
            getQuizById = GetQuizById(quizRepository),
            getAllQuizzes = GetAllQuizzes(quizRepository),
            getUncompletedQuizzes = GetUncompletedQuizzes(quizRepository),
            getCompletedQuizzes = GetCompletedQuizzes(quizRepository),
            deleteQuiz = DeleteQuiz(quizRepository),
            updateQuiz = UpdateQuiz(quizRepository),
            getQuizWithQuestions = GetQuizWithQuestions(quizRepository),
            getAllQuizzesWithQuestions = GetAllQuizzesWithQuestions(quizRepository)
        )
    }
}
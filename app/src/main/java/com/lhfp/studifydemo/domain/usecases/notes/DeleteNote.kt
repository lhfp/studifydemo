package com.lhfp.studifydemo.domain.usecases.notes

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
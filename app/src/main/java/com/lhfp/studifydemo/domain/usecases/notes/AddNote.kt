package com.lhfp.studifydemo.domain.usecases.notes

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.repository.NoteRepository

class AddNote (private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note): Long {
        return repository.insertNote(note)
    }
}
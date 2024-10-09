package com.lhfp.studifydemo.domain.usecases.notes

data class NotesUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote
)

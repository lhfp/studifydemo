package com.lhfp.studifydemo.ui.notes_list

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.util.NoteOrder

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder, val subject: Subject): NoteListEvent()
    data class DeleteNote(val note: Note, val subject: Subject): NoteListEvent()
}
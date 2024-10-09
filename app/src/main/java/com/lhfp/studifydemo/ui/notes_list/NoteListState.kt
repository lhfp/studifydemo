package com.lhfp.studifydemo.ui.notes_list

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.util.NoteOrder
import com.lhfp.studifydemo.domain.util.OrderType

data class NoteListState(
    val notes: MutableList<Note> = mutableListOf(),
    val noteOrder: NoteOrder = NoteOrder.CreatedDate(OrderType.Descending)
)
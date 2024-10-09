package com.lhfp.studifydemo.domain.usecases.notes

import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.repository.NoteRepository
import com.lhfp.studifydemo.domain.util.NoteOrder
import com.lhfp.studifydemo.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(
        subjectId: Int,
        noteOrder: NoteOrder = NoteOrder.CreatedDate(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotesForSubject(subjectId).map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.CreatedDate -> notes.sortedBy { it.createdAt }
                        is NoteOrder.UpdatedDate -> notes.sortedBy { it.updatedAt }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.CreatedDate -> notes.sortedByDescending { it.createdAt }
                        is NoteOrder.UpdatedDate -> notes.sortedByDescending { it.updatedAt }
                    }
                }
            }
        }
    }
}
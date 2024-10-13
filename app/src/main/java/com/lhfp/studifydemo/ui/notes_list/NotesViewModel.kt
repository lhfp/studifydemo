package com.lhfp.studifydemo.ui.notes_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.usecases.notes.NotesUseCases
import com.lhfp.studifydemo.domain.util.NoteOrder
import com.lhfp.studifydemo.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCases: NotesUseCases) : ViewModel() {

    private val _state = mutableStateOf(NoteListState())
    val state: State<NoteListState> = _state

    private val _newNoteId = mutableStateOf<Long?>(null)
    val newNoteId: State<Long?> = _newNoteId

    private var getNotesJob: Job? = null

    private fun getNotes(
        subjectId: Int,
        noteOrder: NoteOrder = NoteOrder.CreatedDate(OrderType.Descending)
    ) {
        getNotesJob?.cancel()
        notesUseCases.getNotes(subjectId, noteOrder).onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes.toMutableList(),
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
        getNotesJob = notesUseCases.getNotes(subjectId, noteOrder).onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes.toMutableList(),
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.DeleteNote -> TODO()
            is NoteListEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class
                    && state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.subject.subjectId, event.noteOrder)
            }
        }
    }

    private fun addNote(note: Note) {
        viewModelScope.launch {
            runCatching {
                notesUseCases.addNote(note).also { _newNoteId.value = it }
            }.onFailure {
                Log.d(TAG, "addNote: ${it.message}")
            }
        }
    }

    fun createEmptyNote(title: String, subjectId: Int) {
        addNote(
            Note(
                title = title,
                content = "",
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                subjectId = subjectId
            )
        ).also {
            getNotes(subjectId)
        }
    }

    fun updateNoteTitle(note: Note, title: String) {
        viewModelScope.launch {
            runCatching {
                notesUseCases.updateNote(note.copy(title = title))
            }
        }
    }

    fun updateNoteContent(note: Note, content: String) {
        viewModelScope.launch {
            runCatching {
                notesUseCases.updateNote(note.copy(content = content))
            }
        }
    }

    companion object {
        private const val TAG = "NotesViewModel"
    }
}
package com.lhfp.studifydemo.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import com.lhfp.studifydemo.domain.util.NoteOrder
import com.lhfp.studifydemo.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val subjectRepository: SubjectRepository) : ViewModel() {

    private val _state = mutableStateOf(SubjectsState())
    val state: State<SubjectsState> = _state

    private var getSubjectsJob: Job? = null

    private val sampleSubjectList = listOf(
        Subject(
            name = "Historia",
            color = R.color.red_200,
            updatedAt = System.currentTimeMillis()
        ),
        Subject(
            name = "Geografia",
            color = R.color.purple_300,
            updatedAt = System.currentTimeMillis()
        ),
        Subject(
            name = "Física",
            color = R.color.bluegray_400,
            updatedAt = System.currentTimeMillis()
        )
    )

    init {
        getSubjects()
    }

    private fun getSubjects(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)) {
        getSubjectsJob?.cancel()
        getSubjectsJob = subjectRepository.getAllSubjects().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.name.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.createdAt }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.name.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.createdAt }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }.onEach { subjects ->
            _state.value = state.value.copy(
                subjects = subjects
            )
        }.launchIn(viewModelScope)
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch {
            subjectRepository.insertSubject(subject)
        }
    }

}
package com.lhfp.studifydemo.ui.subjects

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.usecases.subjects.SubjectsUseCases
import com.lhfp.studifydemo.domain.util.OrderType
import com.lhfp.studifydemo.domain.util.SubjectOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectsViewModel @Inject constructor (private val subjectsUseCases: SubjectsUseCases) : ViewModel() {

    private val _state = mutableStateOf(SubjectsState())
    val state: State<SubjectsState> = _state

    private var getSubjectsJob: Job? = null

    init {
        getSubjects()
    }

    private fun getSubjects(subjectOrder: SubjectOrder = SubjectOrder.Date(OrderType.Descending)) {
        getSubjectsJob?.cancel()
        getSubjectsJob = subjectsUseCases.getSubjectsWithNotes(subjectOrder).onEach { subjects ->
            _state.value = state.value.copy(
                subjectsWithNotes = subjects.toMutableList()
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SubjectEvent)  {
        when (event) {
            is SubjectEvent.Order -> {
                if (state.value.subjectOrder::class == event.subjectOrder::class
                    && state.value.subjectOrder.orderType == event.subjectOrder.orderType){
                    return
                }
                getSubjects(event.subjectOrder)
            }
            is SubjectEvent.DeleteSubject -> {
                viewModelScope.launch {
                    subjectsUseCases.removeSubject(subject = event.subject)
                    getSubjects()
                }
            }
        }
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch {
            runCatching {
                subjectsUseCases.addSubject(subject)
            }.onFailure {
                // TODO: On Failure -> display message somehow
            }
        }
    }
}
package com.lhfp.studifydemo.domain.usecases.subjects

import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import com.lhfp.studifydemo.domain.util.OrderType
import com.lhfp.studifydemo.domain.util.SubjectOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubjectsWithNotes(
    private val repository: SubjectRepository
) {
    operator fun invoke(
        subjectOrder: SubjectOrder = SubjectOrder.Date(OrderType.Descending)
    ): Flow<List<SubjectWithNotes>> {
        return repository.getAllSubjectWithNotes().map { subjects ->
            when (subjectOrder.orderType) {
                is OrderType.Ascending -> {
                    when(subjectOrder) {
                        is SubjectOrder.Title -> subjects.sortedBy { it.subject.name.lowercase() }
                        is SubjectOrder.Date -> subjects.sortedBy { it.subject.createdAt }
                        is SubjectOrder.Color -> subjects.sortedBy { it.subject.color }
                    }
                }
                is OrderType.Descending -> {
                    when(subjectOrder) {
                        is SubjectOrder.Title -> subjects.sortedByDescending { it.subject.name.lowercase() }
                        is SubjectOrder.Date -> subjects.sortedByDescending { it.subject.createdAt }
                        is SubjectOrder.Color -> subjects.sortedByDescending { it.subject.color }
                    }
                }
            }
        }
    }
}
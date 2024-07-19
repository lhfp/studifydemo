package com.lhfp.studifydemo.domain.usecases.subjects

import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.repository.SubjectRepository
import com.lhfp.studifydemo.domain.util.OrderType
import com.lhfp.studifydemo.domain.util.SubjectOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSubjects(
    private val repository: SubjectRepository
) {
    operator fun invoke(
        subjectOrder: SubjectOrder = SubjectOrder.Date(OrderType.Descending)
    ): Flow<List<Subject>> {
        return repository.getAllSubjects().map { subjects ->
            when (subjectOrder.orderType) {
                is OrderType.Ascending -> {
                    when(subjectOrder) {
                        is SubjectOrder.Title -> subjects.sortedBy { it.name.lowercase() }
                        is SubjectOrder.Date -> subjects.sortedBy { it.createdAt }
                        is SubjectOrder.Color -> subjects.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(subjectOrder) {
                        is SubjectOrder.Title -> subjects.sortedByDescending { it.name.lowercase() }
                        is SubjectOrder.Date -> subjects.sortedByDescending { it.createdAt }
                        is SubjectOrder.Color -> subjects.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}
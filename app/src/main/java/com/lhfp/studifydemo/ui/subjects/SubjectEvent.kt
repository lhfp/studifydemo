package com.lhfp.studifydemo.ui.subjects

import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.util.SubjectOrder

sealed class SubjectEvent {
    data class Order(val subjectOrder: SubjectOrder): SubjectEvent()
    data class DeleteSubject(val subject: Subject): SubjectEvent()
}
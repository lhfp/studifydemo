package com.lhfp.studifydemo.ui.subjects

import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.domain.util.OrderType
import com.lhfp.studifydemo.domain.util.SubjectOrder

data class SubjectsState(
    val subjectsWithNotes: MutableList<SubjectWithNotes> = mutableListOf(),
    val subjectOrder: SubjectOrder = SubjectOrder.Date(OrderType.Descending)
)
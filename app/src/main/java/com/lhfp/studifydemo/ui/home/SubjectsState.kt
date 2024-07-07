package com.lhfp.studifydemo.ui.home

import com.lhfp.studifydemo.domain.model.Subject

data class SubjectsState(
    val subjects: List<Subject> = emptyList()
)
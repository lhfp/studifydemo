package com.lhfp.studifydemo.domain.usecases.subjects

import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.repository.SubjectRepository

class RemoveSubject(
    private val repository: SubjectRepository
) {
    suspend operator fun invoke(subject: Subject) {
        repository.deleteSubject(subject)
    }
}
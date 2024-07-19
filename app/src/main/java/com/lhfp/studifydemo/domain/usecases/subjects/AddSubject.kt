package com.lhfp.studifydemo.domain.usecases.subjects

import com.lhfp.studifydemo.domain.model.InvalidSubjectException
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.repository.SubjectRepository

class AddSubject(
    private val repository: SubjectRepository
) {
    @Throws(InvalidSubjectException::class)
    suspend operator fun invoke(subject: Subject) {
        if (subject.name.isBlank()) {
            throw InvalidSubjectException("The subject requires a name")
        }
        if (subject.color == 0) {
            throw InvalidSubjectException("The subject requires a color")
        }
        repository.insertSubject(subject)
    }
}
package com.lhfp.studifydemo

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.domain.model.SubjectWithNotes

class MockDataSources {

    companion object {
        private val GEOGRAPHY_SUBJECT = Subject(
            subjectId = 0,
            name = "Geography",
            color = R.color.purple_300,
            updatedAt = System.currentTimeMillis()
        )

        private val HISTORY_SUBJECT = Subject(
            subjectId = 1,
            name = "History",
            color = R.color.bluegray_400,
            updatedAt = System.currentTimeMillis()
        )

        val ENGLISH_SUBJECT = Subject(
            subjectId = 2,
            name = "English",
            color = R.color.brown_400,
            updatedAt = System.currentTimeMillis()
        )

        private val PORTUGUESE_SUBJECT = Subject(
            subjectId = 3,
            name = "Portuguese",
            color = R.color.lightgreen_400,
            updatedAt = System.currentTimeMillis()
        )

        private val PHYSICS_SUBJECT = Subject(
            subjectId = 4,
            name = "Physics",
            color = R.color.pink_400,
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_1 = Note(
            subjectId = 1,
            title = "Note title 1",
            content =  LoremIpsum().values.last(),
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_2 = Note(
            subjectId = 1,
            title = "Note title 2",
            content = "",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        private val MOCK_NOTE_3 = Note(
            subjectId = 2,
            title = "Note title 3",
            content = "",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        val NOTES_LIST = listOf(
            MOCK_NOTE_1,
            MOCK_NOTE_2,
            MOCK_NOTE_3,
        )

        val SUBJECT_WITH_NOTES_LIST = listOf(
            SubjectWithNotes(
                subject = GEOGRAPHY_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = HISTORY_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = PORTUGUESE_SUBJECT,
                notes = NOTES_LIST
            ),
            SubjectWithNotes(
                subject = PHYSICS_SUBJECT,
                notes = NOTES_LIST
            ),
        )
    }
}
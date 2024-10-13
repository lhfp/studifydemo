package com.lhfp.studifydemo.ui.edit_note

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.ui.edit_note.components.EditNoteHeader
import com.lhfp.studifydemo.ui.notes_list.NotesViewModel
import com.lhfp.studifydemo.ui.notes_list.components.NotesListTopBar
import com.lhfp.studifydemo.ui.subjects.SubjectsViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun EditNoteScreen(
    subjectId: Int,
    noteId: Int,
    navigateBack: () -> Unit,
    subjectsViewModel: SubjectsViewModel = hiltViewModel(),
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val currentSubject =
        subjectsViewModel.state.value.subjectsWithNotes.find { it.subject.subjectId == subjectId }
    val currentNote = currentSubject?.notes?.find { it.noteId == noteId }

    currentNote?.let { note ->
        EditNoteContent(
            subject = currentSubject.subject,
            note = note,
            onNoteTitleChange = {
                notesViewModel.updateNoteTitle(note, it)
            },
            onNoteContentChange = {
                notesViewModel.updateNoteContent(note, it)
            },
            navigateBack = navigateBack,
            modifier = Modifier.imePadding()
        )
    }
}

@Composable
fun EditNoteContent(
    subject: Subject,
    note: Note,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onNoteContentChange: (String) -> Unit = {},
    onNoteTitleChange: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            NotesListTopBar(
                subjectName = subject.name,
                subjectColor = colorResource(subject.color),
                navigateBack = navigateBack
            )
        }
    ) {
        Column(
            modifier
                .padding(it)
                .fillMaxSize()) {
            EditNoteHeader(
                noteTitle = note.title,
                onTitleChange = onNoteTitleChange
            )
            EditNoteTextContent(note.content, onNoteContentChange)
        }
    }

}

@Composable
fun EditNoteTextContent(noteContent: String, onNoteContentChange: (String) -> Unit) {
    var noteText by remember {
        mutableStateOf(noteContent)
    }

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        BasicTextField(
            value = noteText,
            onValueChange = {
                noteText = it
                onNoteContentChange(it)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(name = "LightMode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EditNoteContentPreview() {
    StudifyDemoTheme {
        EditNoteContent(
            subject = MockDataSources.SUBJECT_WITH_NOTES_LIST[0].subject,
            note = MockDataSources.NOTES_LIST[0]
        )
    }
}
package com.lhfp.studifydemo.ui.notes_list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.ui.common.NoteNavigationScreens
import com.lhfp.studifydemo.ui.edit_note.EditNoteScreen
import com.lhfp.studifydemo.ui.notes_list.components.NotesListTopBar
import com.lhfp.studifydemo.ui.subjects.SubjectsViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun NotesScreen(
    subjectId: Int,
    modifier: Modifier = Modifier,
    subjectsViewModel: SubjectsViewModel = hiltViewModel(),
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val selectedSubject =
        subjectsViewModel.state.value.subjectsWithNotes.find { it.subject.subjectId == subjectId }

    selectedSubject?.let { subjectWithNotes ->

        val navController = rememberNavController()
        val emptyTitle = stringResource(id = R.string.empty_note_title)

        StudifyDemoTheme {
            NavHost(
                navController = navController,
                startDestination = NoteNavigationScreens.NavNoteListScreen(subjectId)
            ) {
                composable<NoteNavigationScreens.NavNoteListScreen> {
                    NotesContent(
                        subject = subjectWithNotes,
                        modifier,
                        onNoteCreate = {
                            notesViewModel.createEmptyNote(
                                title = emptyTitle,
                                subjectId = subjectId
                            )
                            navController.navigate(
                                NoteNavigationScreens.NavNoteEditScreen(
                                    noteId = subjectWithNotes.notes.last().noteId
                                )
                            )
                        },
                        onNoteTap = {
                            navController.navigate(
                                NoteNavigationScreens.NavNoteEditScreen(
                                    noteId = it
                                )
                            )
                        }
                    )
                }

                composable<NoteNavigationScreens.NavNoteEditScreen> { backStackEntry ->
                    val args = backStackEntry.toRoute<NoteNavigationScreens.NavNoteEditScreen>()
                    EditNoteScreen(
                        subjectId = subjectId,
                        noteId = args.noteId
                    )
                }
            }
        }
    }
}

@Composable
fun NotesContent(
    subject: SubjectWithNotes,
    modifier: Modifier = Modifier,
    onNoteCreate: () -> Unit,
    onNoteTap: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            NotesListTopBar(
                subjectName = subject.subject.name,
                subjectColor = colorResource(subject.subject.color),
                action = {
                    AnimatedVisibility(visible = true) {
                        IconButton(
                            onClick = onNoteCreate
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NotesList(
                notes = subject.notes,
                subjectColor = colorResource(subject.subject.color),
                createNote = onNoteCreate,
                onNoteTap = onNoteTap
            )
        }
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    subjectColor: Color,
    createNote: () -> Unit,
    onNoteTap: (Int) -> Unit
) {
    AnimatedVisibility(
        visible = notes.isEmpty(),
        enter = fadeIn(tween(400)),
        exit = fadeOut(tween(400))
    ) {
        EmptyNotesScreen(
            subjectColor,
            createNote
        )
    }
    AnimatedVisibility(
        visible = notes.isNotEmpty(),
        enter = fadeIn(tween(400)),
        exit = fadeOut(tween(400))
    ) {
        LazyColumn {
            items(notes) {
                Row(Modifier.animateItem()) {
                    NoteItemView(
                        it,
                        subjectColor,
                        onClick = {
                            onNoteTap(it.noteId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyNotesScreen(subjectColor: Color, createNote: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExtendedFloatingActionButton(
                onClick = {
                    createNote()
                    // TODO: then go to edit note view with that note
                },
                containerColor = subjectColor,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(text = "Tap to create a new note.")
        }
    }
}

@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun GeographyNotesPreview() {
    StudifyDemoTheme {
        NotesContent(
            subject = SubjectWithNotes(
                subject = MockDataSources.SUBJECT_WITH_NOTES_LIST[0].subject,
                notes = MockDataSources.NOTES_LIST
            ),
            onNoteCreate = {},
            onNoteTap = {}
        )
    }
}

@Preview(name = "LightMode", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
private fun PhysicsNotesPreview() {
    StudifyDemoTheme {
        NotesContent(
            subject = SubjectWithNotes(
                subject = MockDataSources.SUBJECT_WITH_NOTES_LIST[3].subject,
                notes = emptyList()
            ),
            onNoteCreate = {},
            onNoteTap = {}
        )
    }
}
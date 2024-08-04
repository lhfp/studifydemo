package com.lhfp.studifydemo.ui.notes

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.ui.common.setEdgeToEdgeWithInsets
import com.lhfp.studifydemo.ui.subjects.SubjectsViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun NotesScreen(
    subjectId: Int,
    modifier: Modifier = Modifier,
    subjectsViewModel: SubjectsViewModel = hiltViewModel()
) {
    val selectedSubject =
        subjectsViewModel.state.value.subjectsWithNotes.find { it.subject.subjectId == subjectId }

    selectedSubject?.let {
        NotesContent(
            subject = it,
            modifier
        )
    }
}

@Composable
fun NotesContent(
    subject: SubjectWithNotes,
    modifier: Modifier = Modifier
) {
    val isInitInDarkTheme = isSystemInDarkTheme()

    // Main Activity
    StudifyDemoTheme(darkTheme = isInitInDarkTheme) {
        Scaffold(
            topBar = {
                SubjectTopAppBar(
                    subjectName = subject.subject.name,
                    subjectColor = colorResource(subject.subject.color)
                )
            }
        ) {
            Column(modifier.fillMaxSize().padding(it)) {
                NotesList(notes = subject.notes)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectTopAppBar(
    subjectName: String,
    subjectColor: Color
) {
    TopAppBar(
        title = {
            Text(
                text = subjectName,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = subjectColor)
    )
}

@Composable
fun NotesList(
    notes: List<Note>
) {
    AnimatedVisibility(
        visible = notes.isEmpty(),
        enter = fadeIn(tween(400)),
        exit = fadeOut(tween(400))
    ) {
        EmptyNotesScreen()
    }
    AnimatedVisibility(
        visible = notes.isNotEmpty(),
        enter = fadeIn(tween(400)),
        exit = fadeOut(tween(400))
    ) {
        LazyColumn {
            items(notes) {

            }
        }
    }
}

@Composable
fun EmptyNotesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExtendedFloatingActionButton(
                onClick = {

                },
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            }
            Text(text = "Tap to create a new note.")
        }
    }
}

@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "LightMode", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Composable
private fun NotesPreview() {
    StudifyDemoTheme {
        NotesContent(
            subject = SubjectWithNotes(
                subject = MockDataSources.ENGLISH_SUBJECT,
                notes = emptyList()
            )
        )
    }
}
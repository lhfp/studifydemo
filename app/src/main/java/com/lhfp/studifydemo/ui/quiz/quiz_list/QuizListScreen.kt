package com.lhfp.studifydemo.ui.quiz.quiz_list

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.ui.quiz.QuizViewModel
import com.lhfp.studifydemo.ui.quiz.UIState
import com.lhfp.studifydemo.ui.quiz.components.QuizLoading
import com.lhfp.studifydemo.ui.quiz.components.StartQuizConfirmDialog
import com.lhfp.studifydemo.ui.subjects.SubjectsViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import com.lhfp.studifydemo.ui.theme.robotoFont

@Composable
fun QuizListScreen(
    onQuizOpen: (Int) -> Unit,
    quizViewModel: QuizViewModel = hiltViewModel(),
    subjectsViewModel: SubjectsViewModel = hiltViewModel()
) {
    val subjectsList = subjectsViewModel.state.value.subjectsWithNotes.map { it.subject }
    val openAlertDialog = remember { mutableStateOf(false) }
    val selectedQuizIndex = remember { mutableStateOf<Int?>(null) }

    QuizListContent(
        quizListState = quizViewModel.quizListState.value,
        subjects = subjectsList,
        onQuizCreate = { selectedSubjectIndex ->
            val selectedSubjectId = subjectsList[selectedSubjectIndex].subjectId
            quizViewModel.generateQuiz(
                subjectContent = quizViewModel.getSubjectContent(
                    subjectsViewModel.state.value.subjectsWithNotes[selectedSubjectIndex]
                ),
                subjectId = selectedSubjectId,
            )
        },
        onQuizClick = {
            selectedQuizIndex.value = it
            openAlertDialog.value = true
        }
    )

    when {
        openAlertDialog.value -> {
            StartQuizConfirmDialog(
                onDismiss = {
                    openAlertDialog.value = false
                    selectedQuizIndex.value = null
                    quizViewModel.onUIEvent(UIState.Initial)
                },
                onConfirm = {
                    selectedQuizIndex.value?.let {
                        Log.i("QuizListScreen", "opening QuizScreen with ID $it")
                        onQuizOpen(it)
                        openAlertDialog.value = false
                        selectedQuizIndex.value = null
                    }
                }
            )
        }
    }
}

@Composable
fun QuizListContent(
    quizListState: QuizListState,
    subjects: List<Subject>,
    modifier: Modifier = Modifier,
    onQuizClick: (Int) -> Unit,
    onQuizCreate: (Int) -> Unit = {}
) {
    val selectedSubjectIndex = remember {
        mutableIntStateOf(0)
    }

    Column(modifier.padding(vertical = 10.dp)) {
        AnimatedVisibility(visible = quizListState.uiState == UIState.Loading) {
            QuizLoading()
        }

        when(quizListState.uiState) {
            UIState.Loading -> return
            is UIState.OnQuizReady -> {
                onQuizClick((quizListState.uiState as UIState.OnQuizReady).newQuizId)
            }
            else -> {}
        }


        if (quizListState.quizzes.isEmpty()) {
            EmptyQuizContent(
                subjects = subjects,
                onSubjectSelected = { selectedSubjectIndex.intValue = it },
                onQuizCreate = {
                    onQuizCreate(selectedSubjectIndex.intValue)
                }
            )
        } else {
            QuizListHeader(
                subjects,
                onSubjectSelected = { selectedSubjectIndex.intValue = it },
                onQuizCreateButton = { onQuizCreate(selectedSubjectIndex.intValue) }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = quizListState.quizzes,
                    key = { it.quiz.quizId }
                ) { quizWithQuestions ->
                    subjects.find { subject -> subject.subjectId == quizWithQuestions.quiz.subjectId }
                        ?.let {
                            QuizItemView(
                                quizWithQuestions = quizWithQuestions,
                                subject = it,
                                onClick = { onQuizClick(quizWithQuestions.quiz.quizId) }
                            )
                        }
                }
            }
        }
    }
}

@Composable
fun EmptyQuizContent(
    subjects: List<Subject>,
    onSubjectSelected: (Int) -> Unit,
    onQuizCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Create a quiz to test your knowledge!",
            style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
        )

        Text(
            text = "Choose a subject:",
            style = MaterialTheme.typography.titleMedium.copy(fontFamily = robotoFont),
            modifier = Modifier.padding(top = 10.dp)
        )

        SubjectSelector(
            subjects = subjects,
            onSubjectSelected = onSubjectSelected,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        ElevatedButton(
            shape = CutCornerShape(5.dp),
            onClick = onQuizCreate
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            Text(text = "Create new Quiz")
        }
    }
}

@Composable
fun QuizListHeader(
    subjects: List<Subject>,
    onSubjectSelected: (Int) -> Unit,
    onQuizCreateButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        FilledTonalButton(onClick = onQuizCreateButton) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
            Text(text = "New Quiz")
        }
        if (subjects.isNotEmpty()) {
            SubjectSelector(subjects, onSubjectSelected)
        }
    }
}

@Composable
fun SubjectSelector(
    subjects: List<Subject>,
    onSubjectSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember {
        mutableIntStateOf(0)
    }

    Log.i("QuizListScreen", subjects.toString())

    if (subjects.isNotEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        isDropDownExpanded.value = true
                    }
            ) {
                Text(
                    text = subjects[itemPosition.intValue].name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    subjects.forEachIndexed { index, subject ->
                        DropdownMenuItem(text = {
                            Text(text = subject.name)
                        }, onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.intValue = index
                            onSubjectSelected(itemPosition.intValue)
                        })
                    }
                }
            }
        }
    }
}

@Preview(name = "QuizListPreview")
@Composable
private fun QuizListPreview() {
    StudifyDemoTheme {
        QuizListContent(
            quizListState = QuizListState(
                quizzes = MockDataSources.QUIZ_WITH_QUESTIONS_LIST
            ),
            subjects = MockDataSources.SUBJECT_WITH_NOTES_LIST.map { it.subject },
            onQuizClick = {}
        )
    }
}

@Preview(name = "QuizListEmptyPreview")
@Composable
private fun QuizListEmptyPreview() {
    StudifyDemoTheme {
        QuizListContent(
            quizListState = QuizListState(
                quizzes = emptyList()
            ),
            subjects = MockDataSources.SUBJECT_WITH_NOTES_LIST.map { it.subject },
            onQuizClick = {}
        )
    }
}
package com.lhfp.studifydemo.ui.subjects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.ui.subjects.components.AddSubjectBottomSheet
import com.lhfp.studifydemo.ui.subjects.components.SubjectView
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import kotlinx.coroutines.launch

@Composable
fun SubjectsScreen(
    viewModel: SubjectsViewModel = hiltViewModel()
) {
    SubjectsContent(
        subjectsState = viewModel.state.value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectsContent(
    subjectsState: SubjectsState,
    onEvent: (SubjectEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { it != SheetValue.PartiallyExpanded }
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        addSubjectView { showBottomSheet = true }
        items(subjectsState.subjectsWithNotes) { subject ->
            SubjectView(
                subject,
                onClick = {

                },
                onDelete = {
                    onEvent(SubjectEvent.DeleteSubject(subject.subject))
                })
        }
    }

    AddSubjectBottomSheet(
        isSheetVisible = showBottomSheet,
        sheetState = sheetState,
        onDismiss = {
            scope.launch { sheetState.hide() }
                .invokeOnCompletion { showBottomSheet = false }
        })
}


fun LazyListScope.addSubjectView(onAddSubject: () -> Unit) {
    item {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 15.dp)
        ) {
            ElevatedButton(onClick = {
                onAddSubject()
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                Text(text = "Add new subject")
            }

            ElevatedButton(onClick = {

            }) {
                Icon(imageVector = Icons.Filled.FilterList, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
private fun SubjectsPreview() {
    StudifyDemoTheme {
        SubjectsContent(
            subjectsState = SubjectsState(
                subjectsWithNotes = MockDataSources.SUBJECT_WITH_NOTES_LIST.toMutableList(),
            ),
            onEvent = {}
        )
    }
}
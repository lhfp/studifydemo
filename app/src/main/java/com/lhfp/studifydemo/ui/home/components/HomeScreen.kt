package com.lhfp.studifydemo.ui.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.ui.home.HomeState
import com.lhfp.studifydemo.ui.home.HomeViewModel
import com.lhfp.studifydemo.ui.home.SubjectEvent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeContent(
        homeState = viewModel.state.value,
        onEvent = { viewModel.onEvent(it) }
    )
}

@Composable
fun HomeContent(
    homeState: HomeState,
    onEvent: (SubjectEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(homeState.subjectsWithNotes) { subject ->
            SubjectView(
                subject,
                onClick = {

                },
                onDelete = {
                    onEvent(SubjectEvent.DeleteSubject(subject.subject))
                })
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeContent(
        homeState = HomeState(
            subjectsWithNotes = MockDataSources.SUBJECT_WITH_NOTES_LIST.toMutableList(),
        ),
        onEvent = {}
    )
}
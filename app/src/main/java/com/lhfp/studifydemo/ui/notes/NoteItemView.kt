package com.lhfp.studifydemo.ui.notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Note

@Composable
fun NoteItemView(note: Note) {

}

@Composable
fun NoteCard(modifier: Modifier = Modifier) {
    
}

@Preview
@Composable
private fun NoteItemViewPreview() {
    NoteItemView(note = MockDataSources.NOTES_LIST[0])
}
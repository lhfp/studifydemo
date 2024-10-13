package com.lhfp.studifydemo.ui.notes_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.util.TimeUtils

@Composable
fun NoteItemView(
    note: Note,
    subjectColor: Color,
    onClick: () -> Unit = {}
) {
    NoteCard(note, subjectColor, onClick = onClick)
}

@Composable
fun NoteCard(
    note: Note,
    subjectColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .heightIn(0.dp, 150.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors().copy(containerColor = subjectColor)
    ) {
        Column(
            Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            NoteHeader(note.title, note.updatedAt)
            Spacer(modifier = modifier.size(10.dp))
            NoteContent(noteContent = note.content)
        }
    }
}

@Composable
fun NoteHeader(title: String, updatedAt: Long) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
        )
        Text(
            text = TimeUtils.getStringDateOf(updatedAt),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )
    }
}

@Composable
fun NoteContent(noteContent: String) {
    Text(
        text = noteContent,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White,
            textAlign = TextAlign.Start
        ),
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview
@Composable
private fun NoteItemViewPreview() {
    NoteItemView(note = MockDataSources.NOTES_LIST[0], subjectColor = colorResource(id = MockDataSources.ENGLISH_SUBJECT.color))
}
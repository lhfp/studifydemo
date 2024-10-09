package com.lhfp.studifydemo.ui.notes_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Note
import com.lhfp.studifydemo.domain.util.TimeUtils
import com.lhfp.studifydemo.ui.theme.robotoFont

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
            .padding(10.dp),
        border = BorderStroke(2.dp, subjectColor),
        onClick = onClick
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
            fontFamily = robotoFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = TimeUtils.getStringDateOf(updatedAt),
            fontFamily = robotoFont,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun NoteContent(noteContent: String) {
    Text(
        text = noteContent,
        fontFamily = robotoFont,
        textAlign = TextAlign.Justify,
        fontWeight = FontWeight.Normal,
        maxLines = 5,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
private fun NoteItemViewPreview() {
    NoteItemView(note = MockDataSources.NOTES_LIST[0], subjectColor = colorResource(id = MockDataSources.ENGLISH_SUBJECT.color))
}
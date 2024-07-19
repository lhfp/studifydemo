package com.lhfp.studifydemo.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.SubjectWithNotes
import com.lhfp.studifydemo.domain.util.TimeUtils
import com.lhfp.studifydemo.ui.common.SwippeableBox

@Composable
fun SubjectView(
    subjectWithNotes: SubjectWithNotes,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    SwippeableBox(
        content = {
            SubjectCard(
                subjectWithNotes = subjectWithNotes,
                onClick = onClick
            )
        },
        swipedContent = {
            DeleteSubjectButton(onClick = {
                onDelete()
                it()
            })
        }
    )
}

@Composable
fun SubjectCard(
    subjectWithNotes: SubjectWithNotes,
    onClick: () -> Unit
) {
    val subject = subjectWithNotes.subject
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = subject.color)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .height(100.dp)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = subject.name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Text(
                    text = subjectWithNotes.notes.count().toString(),
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Outlined.Book,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(
                    text = TimeUtils.getStringDateOf(subject.createdAt),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun SubjectPreview() {
    SubjectView(
        SubjectWithNotes(
            subject = MockDataSources.ENGLISH_SUBJECT,
            notes = MockDataSources.NOTES_LIST
        ), onClick = {}, onDelete = {}
    )
}
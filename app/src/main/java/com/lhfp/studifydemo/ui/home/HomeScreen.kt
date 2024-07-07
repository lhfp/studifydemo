package com.lhfp.studifydemo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    StudifyDemoTheme {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(state.subjects) {
                SubjectView(title = it.name, subjectColor = colorResource(id = it.color))
            }
        }
    }
}

@Composable
fun SubjectView(
    title: String,
    subjectColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = subjectColor
        ),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .height(100.dp)
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier.padding(start = 5.dp, top = 5.dp)
            )
        }

    }
}
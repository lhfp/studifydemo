package com.lhfp.studifydemo.ui.quiz.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import kotlinx.coroutines.delay

@Composable
fun QuizLoading(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
    Box(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
    ) {

        val loadingTexts = listOf(
            stringResource(id = R.string.loading),
            stringResource(id = R.string.reading_notes),
            stringResource(id = R.string.generating_questions)
        )

        var currentShownTextIndex by remember {
            mutableIntStateOf(0)
        }

        AnimatedContent(
            targetState = loadingTexts[currentShownTextIndex],
            label = "loading_animation",
            modifier = Modifier.align(Alignment.Center),
        ) { text ->
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
            LaunchedEffect(key1 = Unit) {
                delay(5000)
                if (currentShownTextIndex < loadingTexts.size - 1) {
                    currentShownTextIndex += 1
                } else {
                    currentShownTextIndex = 0
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizListLoadingPreview() {
    StudifyDemoTheme {
        QuizLoading()
    }
}
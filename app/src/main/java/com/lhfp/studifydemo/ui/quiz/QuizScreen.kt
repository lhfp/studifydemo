package com.lhfp.studifydemo.ui.quiz

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.domain.model.Option
import com.lhfp.studifydemo.domain.model.QuestionWithOptions
import com.lhfp.studifydemo.domain.model.QuizWithQuestions
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

@Composable
fun QuizScreen(
    quizId: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    quizViewModel: QuizViewModel = hiltViewModel()
) {
    StudifyDemoTheme {
        val currentQuiz =
            quizViewModel.quizListState.value.quizzes.find { it.quiz.quizId == quizId }
        currentQuiz?.let {
            QuizScreenContent(
                quiz = it
            )
        }
    }
}

@Composable
fun QuizScreenContent(
    quiz: QuizWithQuestions
) {
    val currentQuizIndex = remember {
        mutableIntStateOf(0)
    }

    val quizProgress = remember {
        mutableFloatStateOf(0.0f)
    }

    val selectedOptionIndex = remember {
        mutableStateOf<Int?>(null)
    }

    Scaffold(topBar = {
        QuizHeader(
            quizTitle = quiz.quiz.name,
            progress = quizProgress.floatValue,
            onBackClick = {

            }
        )
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            QuestionView(
                question = quiz.questions[currentQuizIndex.intValue],
                onOptionSelected = {
                    selectedOptionIndex.value =
                        quiz.questions[currentQuizIndex.intValue].options.indexOf(it)
                },
                selectedOptionIndex = selectedOptionIndex.value,
                onOptionConfirm = {
                    val questionsSize = quiz.questions.count()

                    if ((currentQuizIndex.intValue + 1) < questionsSize) {
                        selectedOptionIndex.value = null
                        currentQuizIndex.intValue += 1
                        quizProgress.floatValue = (currentQuizIndex.intValue.toFloat() / questionsSize)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizHeader(
    quizTitle: String,
    progress: Float,
    onBackClick: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = quizTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            }
        )
        val progressAnimation by animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(durationMillis = 1_000, easing = FastOutSlowInEasing),
            label = "progressAnimation",
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                progress = { progressAnimation },
                drawStopIndicator = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }

    }
}

@Composable
fun QuestionView(
    question: QuestionWithOptions,
    onOptionSelected: (Option) -> Unit,
    onOptionConfirm: () -> Unit,
    selectedOptionIndex: Int?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxHeight(0.86f)
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Text(
            text = question.question.text,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        OptionsView(
            options = question.options,
            onOptionSelected = onOptionSelected,
            selectedOptionIndex = selectedOptionIndex,
            modifier = Modifier
                .fillMaxHeight(0.9f)
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(60.dp),
            onClick = onOptionConfirm,
            enabled = selectedOptionIndex != null
        ) {
            Text(
                text = "Next",
                Modifier.weight(0.8f),
                style = MaterialTheme.typography.headlineMedium
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                contentDescription = "",
                modifier = Modifier.weight(0.2f)
            )
        }
    }
}

@Composable
fun OptionsView(
    options: List<Option>,
    onOptionSelected: (Option) -> Unit,
    selectedOptionIndex: Int?,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        options.forEachIndexed { index, option ->
            OptionView(
                option = option,
                isSelected = selectedOptionIndex == index,
                onClick = { onOptionSelected(option) }
            )
        }
    }
}

@Composable
fun OptionView(
    option: Option,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = if (isSelected) {
            CardDefaults.cardColors()
                .copy(containerColor = MaterialTheme.colorScheme.primaryContainer)
        } else {
            CardDefaults.cardColors()
        },
        modifier = Modifier
            .height(75.dp)
            .fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = option.answer,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun QuizScreenContentPreview() {
    StudifyDemoTheme {
        QuizScreenContent(
            quiz = MockDataSources.QUIZ_WITH_QUESTIONS_LIST[0]
        )
    }
}
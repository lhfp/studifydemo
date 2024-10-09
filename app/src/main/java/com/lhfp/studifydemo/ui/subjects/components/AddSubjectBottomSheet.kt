package com.lhfp.studifydemo.ui.subjects.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.domain.model.Subject
import com.lhfp.studifydemo.ui.subjects.SubjectsViewModel
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import com.lhfp.studifydemo.ui.theme.playFairFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubjectBottomSheet(
    isSheetVisible: Boolean,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SubjectsViewModel = hiltViewModel()
) {
    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = sheetState,
            shape = RoundedCornerShape(10.dp),
            dragHandle = null,
            scrimColor = Color.Black.copy(alpha = .5f),
            content = {
                AddSubjectView(modifier) {
                    viewModel.addSubject(it)
                    onDismiss()
                }
            },
            modifier = Modifier.fillMaxHeight(0.7f)
        )
    }
}

@Composable
fun AddSubjectView(
    modifier: Modifier = Modifier,
    onClick: (subject: Subject) -> Unit
) {
    val subjectNameTextMaxLength = 20
    var subjectNameText by rememberSaveable { mutableStateOf("") }
    var selectedColor by rememberSaveable { mutableIntStateOf(0) }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(top = 15.dp, start = 15.dp)
    ) {
        Column(
            modifier = modifier
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = stringResource(id = R.string.new_subject_title),
                fontFamily = playFairFont,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = subjectNameText,
                onValueChange = {
                    if (it.length <= subjectNameTextMaxLength) {
                        subjectNameText = it
                    }
                },
                label = { Text(text = stringResource(id = R.string.add_subject_name_field)) },
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Book,
                        contentDescription = ""
                    )
                },
                supportingText = {
                    Text(
                        text = "${subjectNameText.length} / $subjectNameTextMaxLength",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ColorSelector(
                currentSelectedColor = selectedColor,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onColorChange = { selectedColor = it }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    onClick(
                        Subject(
                            name = subjectNameText,
                            color = selectedColor,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 25.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.add)
                    )
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = ""
                    )
                }
            )
        }
    }
}

@Composable
fun ColorItem(
    isSelected: Boolean,
    colorValue: Int,
    onColorSelected: (newColor: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = colorValue)),
        border = if (isSelected) BorderStroke(
            5.dp,
            MaterialTheme.colorScheme.primary
        ) else null,
        onClick = { onColorSelected(colorValue) }
    ) {}
}

@Composable
fun ColorSelector(
    currentSelectedColor: Int,
    onColorChange: (newColor: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorList: List<Int> = listOf(
        R.color.red_200,
        R.color.lightblue_400,
        R.color.green_400,
        R.color.yellow_300,
        R.color.purple_400,
        R.color.pink_300,
        R.color.orange_400,
        R.color.bluegray_400,
        R.color.brown_400,
        R.color.pink_400,
        R.color.green_300,
        R.color.red_300,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier.padding(start = 25.dp, end = 25.dp)
    ) {
        items(colorList) { color ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .height(50.dp)
                    .aspectRatio(1f)

            ) {
                ColorItem(
                    isSelected = color == currentSelectedColor,
                    colorValue = color,
                    onColorSelected = onColorChange
                )
            }
        }
    }
}

@Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LightMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AddSubjectViewPreview() {
    AddSubjectView {}
}
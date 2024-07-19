package com.lhfp.studifydemo.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun SwippeableBox(
    content: @Composable () -> Unit,
    swipedContent: @Composable (swipeOut: () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val maxSwipe = -200f
    val minSwipe = 0f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        offsetX = if (offsetX < -100f)
                            maxSwipe
                        else
                            minSwipe
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX = (offsetX + dragAmount).coerceIn(maxSwipe, minSwipe)
                }
            }
    ) {
        Box(
            modifier = modifier
                .offset {
                    if (offsetX < -100) {
                        IntOffset(offsetX.roundToInt(), 0)
                    } else {
                        IntOffset(offsetX.roundToInt(), 0)
                    }
                }
        ) {
            content()
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        ) {
            val density = LocalDensity.current
            AnimatedVisibility(
                visible = offsetX == maxSwipe,
                enter = slideInHorizontally {
                    with(density) { -40.dp.roundToPx() }
                } + expandHorizontally(expandFrom = Alignment.End)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut()
            ) {
                swipedContent {
                    offsetX = minSwipe
                }
            }
        }
    }
}
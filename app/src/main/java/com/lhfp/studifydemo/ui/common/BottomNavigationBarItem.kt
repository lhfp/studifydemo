package com.lhfp.studifydemo.ui.common

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class BottomNavigationBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val screenObject: Any
)

@Serializable
object NavHome

@Serializable
object NavQuiz

@Serializable
object NavStats

@Serializable
object NavProfile

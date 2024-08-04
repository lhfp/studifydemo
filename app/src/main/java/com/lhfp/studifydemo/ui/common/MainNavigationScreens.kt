package com.lhfp.studifydemo.ui.common

import kotlinx.serialization.Serializable

sealed class MainNavigationScreens {
    @Serializable
    object NavMainScreen

    @Serializable
    data class NavNoteListScreen(val subjectId: Int)

    @Serializable
    object NavNoteEditScreen
}


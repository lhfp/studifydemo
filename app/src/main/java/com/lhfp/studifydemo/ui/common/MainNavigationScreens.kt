package com.lhfp.studifydemo.ui.common

import kotlinx.serialization.Serializable

sealed class MainNavigationScreens {
    @Serializable
    object NavMainScreen

    @Serializable
    data class NavNotesScreen(val subjectId: Int)
}

sealed class NoteNavigationScreens {
    @Serializable
    data class NavNoteListScreen(val subjectId: Int)

    @Serializable
    data class NavNoteEditScreen(val noteId: Int)
}


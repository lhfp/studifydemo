package com.lhfp.studifydemo.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.ui.common.NavHome
import com.lhfp.studifydemo.ui.common.NavQuiz
import com.lhfp.studifydemo.ui.common.NavStats
import com.lhfp.studifydemo.ui.common.NavSubjects
import com.lhfp.studifydemo.ui.common.setEdgeToEdgeWithInsets
import com.lhfp.studifydemo.ui.main.components.Logo
import com.lhfp.studifydemo.ui.main.components.SettingsButton
import com.lhfp.studifydemo.ui.main.components.StudifyBottomNavigationBar
import com.lhfp.studifydemo.ui.main.components.StudifyTopBar
import com.lhfp.studifydemo.ui.subjects.SubjectsContent
import com.lhfp.studifydemo.ui.subjects.SubjectsScreen
import com.lhfp.studifydemo.ui.subjects.SubjectsState
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeToEdgeWithInsets()
        setContent {
            MainScreen(
                subjectsScreen = { SubjectsScreen() }
            )
        }
    }

    @Composable
    fun MainScreen(
        subjectsScreen: @Composable () -> Unit,
    ) {
        val isInitInDarkTheme = isSystemInDarkTheme()

        // Main Activity
        StudifyDemoTheme(darkTheme = isInitInDarkTheme) {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    StudifyBottomNavigationBar(navController)
                },
                topBar = {
                    StudifyTopBar(logo = { Logo() }, rightSide = { SettingsButton() })
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavHome,
                        enterTransition = {
                            fadeIn(animationSpec = tween(300))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(300))
                        }
                    ) {
                        composable<NavHome> { Text(text = "Home screen") }
                        composable<NavSubjects> { subjectsScreen() }
                        composable<NavQuiz> { Text(text = "Quiz screen") }
                        composable<NavStats> { Text(text = "Stats screen") }
                    }
                }
            }
        }
    }

    @Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview(name = "LightMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun MainPreview() {
        MainScreen(
            subjectsScreen = {
                SubjectsContent(
                    subjectsState = SubjectsState(
                        subjectsWithNotes = MockDataSources.SUBJECT_WITH_NOTES_LIST.toMutableList(),
                    ),
                    onEvent = {}
                )
            }
        )
    }
}

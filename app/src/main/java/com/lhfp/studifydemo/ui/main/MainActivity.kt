package com.lhfp.studifydemo.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.navigation.toRoute
import com.lhfp.studifydemo.MockDataSources
import com.lhfp.studifydemo.ui.common.MainNavigationScreens
import com.lhfp.studifydemo.ui.common.NavHome
import com.lhfp.studifydemo.ui.common.NavQuiz
import com.lhfp.studifydemo.ui.common.NavStats
import com.lhfp.studifydemo.ui.common.NavSubjects
import com.lhfp.studifydemo.ui.common.setEdgeToEdgeWithInsets
import com.lhfp.studifydemo.ui.main.components.Logo
import com.lhfp.studifydemo.ui.main.components.SettingsButton
import com.lhfp.studifydemo.ui.main.components.StudifyBottomNavigationBar
import com.lhfp.studifydemo.ui.main.components.StudifyTopBar
import com.lhfp.studifydemo.ui.notes_list.NotesScreen
import com.lhfp.studifydemo.ui.quiz.QuizScreen
import com.lhfp.studifydemo.ui.quiz.quiz_list.QuizListScreen
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

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = MainNavigationScreens.NavMainScreen
            ) {
                composable<MainNavigationScreens.NavMainScreen> {
                    MainScreen(
                        homeScreen = { Text(text = "Home screen") },
                        subjectsScreen = {
                            SubjectsScreen(onSubjectClick = {
                                navController.navigate(MainNavigationScreens.NavNotesScreen(it))
                            })
                        },
                        quizScreen = {
                            QuizListScreen(
                                onQuizOpen = {
                                    navController.navigate(MainNavigationScreens.NavQuizScreen(it))
                                }
                            )
                        },
                        statsScreen = { Text(text = "Stats screen") }
                    )
                }

                composable<MainNavigationScreens.NavNotesScreen> { backStackEntry ->
                    val args = backStackEntry.toRoute<MainNavigationScreens.NavNotesScreen>()
                    NotesScreen(
                        subjectId = args.subjectId,
                        navigateBack = { navController.navigateUp() }
                    )
                }

                composable<MainNavigationScreens.NavQuizScreen> { backStackEntry ->
                    val args = backStackEntry.toRoute<MainNavigationScreens.NavQuizScreen>()
                    QuizScreen(
                        quizId = args.quizId,
                        navigateBack = { navController.navigateUp() }
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        homeScreen: @Composable () -> Unit,
        subjectsScreen: @Composable () -> Unit,
        quizScreen: @Composable () -> Unit,
        statsScreen: @Composable () -> Unit
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
                        composable<NavHome> {
                            BackHandler {
                                this@MainActivity.finish()
                            }
                            homeScreen()
                        }
                        composable<NavSubjects> {
                            BackHandler {
                                this@MainActivity.finish()
                            }
                            subjectsScreen()
                        }
                        composable<NavQuiz> {
                            BackHandler {
                                this@MainActivity.finish()
                            }
                            quizScreen()
                        }
                        composable<NavStats> {
                            BackHandler {
                                this@MainActivity.finish()
                            }
                            statsScreen()
                        }
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
            homeScreen = {},
            subjectsScreen = {
                SubjectsContent(
                    subjectsState = SubjectsState(
                        subjectsWithNotes = MockDataSources.SUBJECT_WITH_NOTES_LIST.toMutableList(),
                    ), onEvent = {}, onAddSubjectClick = {}, bottomSheet = {}, onSubjectClick = {}
                )
            },
            quizScreen = {},
            statsScreen = {}
        )
    }
}

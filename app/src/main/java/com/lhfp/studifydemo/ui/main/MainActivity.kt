package com.lhfp.studifydemo.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.ui.common.BottomNavigationBarItem
import com.lhfp.studifydemo.ui.common.NavHome
import com.lhfp.studifydemo.ui.common.NavQuiz
import com.lhfp.studifydemo.ui.common.NavStats
import com.lhfp.studifydemo.ui.common.NavSubjects
import com.lhfp.studifydemo.ui.home.components.AddSubjectBottomSheet
import com.lhfp.studifydemo.ui.main.components.Logo
import com.lhfp.studifydemo.ui.main.components.SettingsButton
import com.lhfp.studifydemo.ui.main.components.StudifyTopBar
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import com.lhfp.studifydemo.ui.theme.robotoFont
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        val isInitInDarkTheme = isSystemInDarkTheme()

        val navigationItems = listOf(
            BottomNavigationBarItem(
                title = stringResource(R.string.main_home),
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                screenObject = NavHome
            ),
            BottomNavigationBarItem(
                title = stringResource(R.string.main_subjects),
                selectedIcon = Icons.AutoMirrored.Filled.InsertDriveFile,
                unselectedIcon = Icons.AutoMirrored.Outlined.InsertDriveFile,
                screenObject = NavSubjects
            ),
            BottomNavigationBarItem(
                title = stringResource(R.string.main_quiz),
                selectedIcon = Icons.Filled.AutoAwesome,
                unselectedIcon = Icons.Outlined.AutoAwesome,
                screenObject = NavQuiz
            ),
            BottomNavigationBarItem(
                title = stringResource(R.string.main_stats),
                selectedIcon = Icons.Filled.QueryStats,
                unselectedIcon = Icons.Outlined.QueryStats,
                screenObject = NavStats
            )
        )

        // Main Activity
        StudifyDemoTheme(darkTheme = isInitInDarkTheme) {
            var selectedNavigationIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            val navController = rememberNavController()
            val sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true,
                confirmValueChange = { it != SheetValue.PartiallyExpanded }
            )
            val scope = rememberCoroutineScope()
            var showBottomSheet by rememberSaveable { mutableStateOf(false) }

            Scaffold(
                bottomBar = {
                    NavigationBar {
                        navigationItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedNavigationIndex == index,
                                label = {
                                    Text(
                                        text = item.title,
                                        fontFamily = robotoFont,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    selectedNavigationIndex = index
                                    navController.navigate(item.screenObject)
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedNavigationIndex) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = "icon"
                                    )
                                })
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            showBottomSheet = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add subject"
                        )
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
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
                            //HomeScreen()
                        }
                        composable<NavSubjects> { Text(text = "Subjects screen") }
                        composable<NavQuiz> { Text(text = "Quiz screen") }
                        composable<NavStats> { Text(text = "Stats screen") }
                    }

                    // Add Subject bottom sheet
                    AddSubjectBottomSheet(
                        isSheetVisible = showBottomSheet,
                        sheetState = sheetState,
                        onDismiss = {
                            scope.launch { sheetState.hide() }
                                .invokeOnCompletion { showBottomSheet = false }
                        })
                }
            }
        }
    }

    @Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview(name = "LightMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun MainPreview() {
        MainScreen()
    }
}
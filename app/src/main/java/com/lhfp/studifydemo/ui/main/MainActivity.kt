package com.lhfp.studifydemo.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.ui.common.BottomNavigationBarItem
import com.lhfp.studifydemo.ui.common.Logo
import com.lhfp.studifydemo.ui.common.NavHome
import com.lhfp.studifydemo.ui.common.NavProfile
import com.lhfp.studifydemo.ui.common.NavQuiz
import com.lhfp.studifydemo.ui.common.NavStats
import com.lhfp.studifydemo.ui.common.SearchBar
import com.lhfp.studifydemo.ui.home.HomeScreen
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme
import com.lhfp.studifydemo.ui.theme.robotoFont

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        val isInitInDarkTheme = isSystemInDarkTheme()

        // Main Activity
        StudifyDemoTheme(darkTheme = isInitInDarkTheme) {
            var selectedNavigationIndex by rememberSaveable {
                mutableIntStateOf(0)
            }

            val navigationItems = listOf(
                BottomNavigationBarItem(
                    title = stringResource(R.string.main_home),
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    screenObject = NavHome
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
                ),
                BottomNavigationBarItem(
                    title = stringResource(R.string.main_profile),
                    selectedIcon = Icons.Filled.AccountCircle,
                    unselectedIcon = Icons.Outlined.AccountCircle,
                    screenObject = NavProfile
                )
            )

            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        navigationItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedNavigationIndex == index,
                                label = {
                                    Text(
                                        text = item.title,
                                        fontFamily = robotoFont,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                },
                                colors = NavigationBarItemColors(
                                    selectedIconColor = MaterialTheme.colorScheme.surface,
                                    unselectedIconColor = MaterialTheme.colorScheme.surface,
                                    unselectedTextColor = MaterialTheme.colorScheme.surface,
                                    disabledIconColor = MaterialTheme.colorScheme.surface,
                                    disabledTextColor = MaterialTheme.colorScheme.surface,
                                    selectedTextColor = MaterialTheme.colorScheme.surface,
                                    selectedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                                ),
                                onClick = {
                                    selectedNavigationIndex = index
                                    navController.navigate(item.screenObject)
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedNavigationIndex) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = "icon",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                })
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            // TODO
                        },
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add subject",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(it)
                ) {
                    TopBar(Modifier.padding(15.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
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
                        composable<NavQuiz> { Text(text = "Quiz screen") }
                        composable<NavStats> { Text(text = "Stats screen") }
                        composable<NavProfile> { Text(text = "Profile screen") }
                    }
                }
            }
        }
    }

    @Composable
    fun TopBar(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxWidth()) {
            Logo(Modifier.align(Alignment.CenterStart))
            SearchBar(Modifier.align(Alignment.CenterEnd))
        }
    }

    @Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Preview(name = "LightMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun MainPreview() {
        MainScreen()
    }
}
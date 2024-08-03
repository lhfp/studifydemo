package com.lhfp.studifydemo.ui.main.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.automirrored.outlined.InsertDriveFile
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lhfp.studifydemo.R
import com.lhfp.studifydemo.ui.common.BottomNavigationBarItem
import com.lhfp.studifydemo.ui.common.NavHome
import com.lhfp.studifydemo.ui.common.NavQuiz
import com.lhfp.studifydemo.ui.common.NavStats
import com.lhfp.studifydemo.ui.common.NavSubjects
import com.lhfp.studifydemo.ui.theme.robotoFont

@Composable
fun StudifyBottomNavigationBar(
    navController: NavController
) {
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
}
package com.lhfp.studifydemo.ui.common

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat

fun ComponentActivity.setEdgeToEdgeWithInsets() {
    this.enableEdgeToEdge(statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))
    WindowCompat.setDecorFitsSystemWindows(window, false)
}
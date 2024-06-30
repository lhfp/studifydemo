package com.lhfp.studifydemo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lhfp.studifydemo.ui.theme.StudifyDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudifyDemoTheme {
            }
        }
    }
}
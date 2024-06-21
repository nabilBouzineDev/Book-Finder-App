package com.nabilbdev.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nabilbdev.bookfinder.ui.BookFinderApp
import com.nabilbdev.bookfinder.ui.theme.BookFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookFinderTheme {
                BookFinderApp()
            }
        }
    }
}
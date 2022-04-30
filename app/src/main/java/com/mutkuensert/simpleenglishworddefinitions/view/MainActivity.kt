package com.mutkuensert.simpleenglishworddefinitions.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mutkuensert.simpleenglishworddefinitions.MainScreen
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.SimpleEnglishWordDefinitionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleEnglishWordDefinitionsTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}
package com.mutkuensert.simpleenglishworddefinitions.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mutkuensert.simpleenglishworddefinitions.MainScreen
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.SimpleEnglishWordDefinitionsTheme
import com.mutkuensert.simpleenglishworddefinitions.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: MainScreenViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            SimpleEnglishWordDefinitionsTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
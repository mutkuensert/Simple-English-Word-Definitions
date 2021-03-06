package com.mutkuensert.simpleenglishworddefinitions.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mutkuensert.simpleenglishworddefinitions.MainScreen
import com.mutkuensert.simpleenglishworddefinitions.ui.theme.SimpleEnglishWordDefinitionsTheme
import com.mutkuensert.simpleenglishworddefinitions.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)

        setContent {
            SimpleEnglishWordDefinitionsTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(applicationContext ,viewModel = viewModel)
            }
        }
    }
}
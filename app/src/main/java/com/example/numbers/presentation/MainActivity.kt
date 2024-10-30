package com.example.numbers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.numbers.NumbersApplication
import com.example.numbers.data.repository.FactsRepository
import com.example.numbers.presentation.ui.screens.MainScreen
import com.example.numbers.presentation.ui.theme.NumbersTheme
import com.example.numbers.presentation.viewModels.MainViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val factsRepository = FactsRepository(
            roomDataSource = (application as NumbersApplication).roomDataSource,
            retrofitDataSource = (application as NumbersApplication).retrofitDataSource
        )
        val mainViewModel by viewModels<MainViewModel> {
            MainViewModel.createFactory(factsRepository)
        }

        setContent {
            NumbersTheme {
                MainScreen(
                    viewModel = mainViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

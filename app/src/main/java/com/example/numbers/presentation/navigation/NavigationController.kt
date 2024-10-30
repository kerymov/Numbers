package com.example.numbers.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbers.presentation.ui.screens.FactDetailsScreen
import com.example.numbers.presentation.ui.screens.MainScreen
import com.example.numbers.presentation.viewModels.MainViewModel

@Composable
fun NavigationController(
    startDestination: Route,
    viewModel: MainViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Route.MAIN.name) {
            MainScreen(
                viewModel = viewModel,
                navigateToDetails = { navController.navigate(Route.FACT_DETAILS.name) },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Route.FACT_DETAILS.name) {
            FactDetailsScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.navigate(Route.MAIN.name) {
                        popUpTo(Route.FACT_DETAILS.name) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
package com.example.numbers.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numbers.presentation.ui.components.CenterAlignedTopBar
import com.example.numbers.presentation.ui.models.FactUi
import com.example.numbers.presentation.viewModels.MainViewModel

@Composable
fun FactDetailsScreen(
    viewModel: MainViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopBar(
                title = uiState.value.currentFact?.number.toString(),
                onNavigationButtonClick = onBackClick
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        FactDescriptionCard(
            text = uiState.value.currentFact?.text.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(vertical = 32.dp, horizontal = 20.dp)
        )
    }
}

@Composable
private fun FactDescriptionCard(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}
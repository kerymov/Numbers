package com.example.numbers.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.numbers.presentation.ui.components.CenterAlignedTopBar
import com.example.numbers.presentation.ui.models.FactUi

@Composable
fun FactDetailsScreen(
    fact: FactUi,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopBar(
                title = fact.number.toString(),
                onNavigationButtonClick = onBackClick
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        FactDescriptionCard(
            text = fact.text,
            modifier = Modifier
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
            style = MaterialTheme.typography.titleMedium
        )
    }
}
package com.example.numbers.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Button(
    onClick = onClick,
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.secondary)
    ),
    shape = RoundedCornerShape(16.dp),
    modifier = modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )
}
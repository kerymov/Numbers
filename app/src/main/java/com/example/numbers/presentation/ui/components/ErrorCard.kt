package com.example.numbers.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.numbers.R

@Composable
fun ErrorCard(
    text: String,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Card(
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.error
    ),
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 8.dp)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Error,
            contentDescription = stringResource(R.string.error),
            tint = MaterialTheme.colorScheme.onError
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onError,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = stringResource(R.string.close),
            tint = MaterialTheme.colorScheme.onError,
            modifier = Modifier
                .align(Alignment.Top)
                .clickable { onCloseClick() }
        )
    }
}
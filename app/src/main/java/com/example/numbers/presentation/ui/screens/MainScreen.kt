package com.example.numbers.presentation.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.numbers.presentation.ui.components.CommonButton
import com.example.numbers.presentation.ui.models.FactUi
import com.example.numbers.presentation.ui.utils.tapGesturesDetector

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val faceSearchItems = listOf(
        FactUi(1, 7680, "7680 is the number of possible rook moves on a 16×16 chessboard."),
        FactUi(2, 4264, "4264 is a number whose sum of squares of the divisors is a square."),
        FactUi(3, 0, "0 is the coldest possible temperature old the Kelvin scale."),
        FactUi(4, -1, "-1 is an uninteresting number."),
        FactUi(5, 7680, "7680 is the number of possible rook moves on a 16×16 chessboard."),
        FactUi(6, 4264, "4264 is a number whose sum of squares of the divisors is a square."),
        FactUi(7, 0, "0 is the coldest possible temperature old the Kelvin scale."),
        FactUi(8, -1, "-1 is an uninteresting number."),
        FactUi(9, 7680, "7680 is the number of possible rook moves on a 16×16 chessboard."),
        FactUi(10, 4264, "4264 is a number whose sum of squares of the divisors is a square."),
        FactUi(11, 0, "0 is the coldest possible temperature old the Kelvin scale."),
        FactUi(12, -1, "-1 is an uninteresting number.")
    )
    var search by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            FactSearch(
                search = search,
                onSearchChange = { search = it },
                onGetFactClick = { print(it) },
                onGetRandomFactClick = { },
                modifier = Modifier.tapGesturesDetector {
                    focusManager.clearFocus()
                }
            )
        },
        modifier = modifier
            .fillMaxSize()
            .tapGesturesDetector {
                focusManager.clearFocus()
            }
    ) { innerPadding ->
        if (faceSearchItems.isEmpty()) {
            EmptySearchHistory(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            SearchHistory(
                items = faceSearchItems,
                onItemClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun FactSearch(
    search: String,
    onSearchChange: (String) -> Unit,
    onGetFactClick: (String) -> Unit,
    onGetRandomFactClick: () -> Unit,
    modifier: Modifier = Modifier
) = Surface(
    color = MaterialTheme.colorScheme.primary,
    modifier = modifier.fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(
                start = 20.dp,
                top = 36.dp,
                end = 20.dp,
                bottom = 12.dp
            )
    ) {
        Text(
            text = "Search a fact",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "by number",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.size(24.dp))
        TextField(
            value = search,
            onValueChange = onSearchChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            placeholder = {
                Text(
                    text = "Enter a number",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        CommonButton(
            title = "Get fact",
            onClick = { onGetFactClick(search) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(12.dp))
        CommonButton(
            title = "Get fact about random number",
            onClick = onGetRandomFactClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchHistory(
    items: List<FactUi>,
    onItemClick: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier.fillMaxWidth()
) {
    stickyHeader {
        Text(
            text = "Search history",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
                .padding(vertical = 12.dp, horizontal = 20.dp)
        )
    }

    items(
        items = items,
        key = { item -> item.id }
    ) { item ->
        SearchItem(
            item = item,
            onClick = { onItemClick(item.id) },
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 4.dp
            )
        )
    }
}

@Composable
private fun SearchItem(
    item: FactUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Card(
    onClick = onClick,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface
    ),
    modifier = modifier.fillMaxWidth()
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(
            text = item.number.toString(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.fact,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun EmptySearchHistory(
    modifier: Modifier = Modifier
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.size(36.dp))

        Text(
            text = "Here you will see the history of your searches",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
    }
}
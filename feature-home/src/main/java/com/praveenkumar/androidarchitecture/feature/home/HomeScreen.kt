package com.praveenkumar.androidarchitecture.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when(effect) {
                is HomeUiEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message ?: "An error occurred")
                }
            }

        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        HomeContent(
            modifier = modifier.padding(innerPadding),
            uiState = uiState
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
            uiState.feed.isEmpty() -> {
                Text(
                    text = "No items available",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else -> {
                Feed(feed = uiState.feed)
            }
        }
    }
}

@Composable
private fun Feed(
    feed: List<FeedItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = feed,
            key = { it.id }
        ) { item ->
            FeedCard(item = item)
        }
    }
}

@Composable
private fun FeedCard(
    item: FeedItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
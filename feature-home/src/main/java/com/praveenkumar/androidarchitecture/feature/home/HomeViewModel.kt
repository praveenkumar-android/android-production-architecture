package com.praveenkumar.androidarchitecture.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveenkumar.androidarchitecture.domain.feed.usecase.ObserveFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val observeFeedUseCase: ObserveFeedUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        onIntent(HomeUiEvent.Load)
    }

    fun onIntent(event: HomeUiEvent) {
        when(event) {
            HomeUiEvent.Load -> observeFeed()
            HomeUiEvent.Refresh -> observeFeed()
        }
    }

    private fun observeFeed() {
        viewModelScope.launch {
            // Show loading state
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessages = null
            )

            // Collect feed data
            observeFeedUseCase.invoke().collect { feed ->
                _uiState.value = _uiState.value.copy (
                    isLoading = false,
                    feed = feed
                )
            }
        }
    }
}
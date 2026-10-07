package com.praveenkumar.androidarchitecture.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveenkumar.androidarchitecture.domain.feed.usecase.ObserveFeedUseCase
import com.praveenkumar.androidarchitecture.domain.feed.usecase.RefreshFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val observeFeedUseCase: ObserveFeedUseCase,
    private val refreshFeedUseCase: RefreshFeedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<HomeUiEffect>()
    val effect = _effect.asSharedFlow()

    init {
        observeFeed()
        refresh()
    }

    fun onIntent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Load -> Unit
            HomeUiEvent.Refresh -> refresh()
        }
    }

    private fun observeFeed() {
        viewModelScope.launch {
            observeFeedUseCase()
                .collect { feed ->
                    _uiState.update {
                        it.copy(
                            feed = feed,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                refreshFeedUseCase()

                _uiState.update {
                    it.copy(isLoading = false)
                }
            } catch (exception: Exception) {
                val message = exception.message ?: "Unable to refresh feed"

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = message
                    )
                }

                _effect.emit(
                    HomeUiEffect.ShowError(message = message)
                )
            }
        }
    }
}
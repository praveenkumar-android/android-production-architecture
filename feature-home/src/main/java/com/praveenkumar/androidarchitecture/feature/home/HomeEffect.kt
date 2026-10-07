package com.praveenkumar.androidarchitecture.feature.home

sealed interface HomeEffect {
    data class ShowError(val message: String) : HomeEffect
}
package com.nikitabolshakov.weather.presentation.state

import com.nikitabolshakov.weather.data.model.local.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
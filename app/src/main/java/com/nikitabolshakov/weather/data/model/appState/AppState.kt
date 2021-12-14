package com.nikitabolshakov.weather.data.model.appState

import com.nikitabolshakov.weather.data.model.Weather

sealed class AppState {
    data class Success(val weatherData: MutableList<Weather>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
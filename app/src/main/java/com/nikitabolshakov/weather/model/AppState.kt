package com.nikitabolshakov.weather.model

import com.nikitabolshakov.weather.model.data.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
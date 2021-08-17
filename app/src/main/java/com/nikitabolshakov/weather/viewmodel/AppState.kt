package com.nikitabolshakov.weather.viewmodel

import com.nikitabolshakov.weather.model.data.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
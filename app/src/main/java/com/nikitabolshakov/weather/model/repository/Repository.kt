package com.nikitabolshakov.weather.model.repository

import com.nikitabolshakov.weather.model.data.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}
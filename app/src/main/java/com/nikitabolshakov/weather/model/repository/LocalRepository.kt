package com.nikitabolshakov.weather.model.repository

import com.nikitabolshakov.weather.model.data.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}
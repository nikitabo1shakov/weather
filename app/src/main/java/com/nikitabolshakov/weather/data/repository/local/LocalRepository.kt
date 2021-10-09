package com.nikitabolshakov.weather.data.repository.local

import com.nikitabolshakov.weather.data.models.local.Weather

interface LocalRepository {

    fun saveEntity(weather: Weather)
    fun getAllHistory(): List<Weather>
}
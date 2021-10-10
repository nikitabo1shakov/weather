package com.nikitabolshakov.weather.data.repository.local

import com.nikitabolshakov.weather.data.model.local.Weather

interface LocalRepository {

    fun saveEntity(weather: Weather)
    fun getAllHistory(): List<Weather>
}
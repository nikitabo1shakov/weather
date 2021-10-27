package com.nikitabolshakov.weather.data.repository.local

import com.nikitabolshakov.weather.data.model.Weather

interface LocalRepository {

    fun saveEntity(weather: Weather)
    fun getAllHistory(): MutableList<Weather>
}
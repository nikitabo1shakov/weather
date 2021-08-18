package com.nikitabolshakov.weather.model.repository

import com.nikitabolshakov.weather.model.data.Weather
import com.nikitabolshakov.weather.model.utils.convertHistoryEntityToWeather
import com.nikitabolshakov.weather.model.utils.convertWeatherToEntity
import com.nikitabolshakov.weather.room.HistoryDao

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        return localDataSource.insert(convertWeatherToEntity(weather))
    }
}
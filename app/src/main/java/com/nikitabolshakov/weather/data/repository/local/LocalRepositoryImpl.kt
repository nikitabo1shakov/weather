package com.nikitabolshakov.weather.data.repository.local

import com.nikitabolshakov.weather.data.models.local.Weather
import com.nikitabolshakov.weather.data.room.HistoryDao
import com.nikitabolshakov.weather.utils.convertHistoryEntityToWeather
import com.nikitabolshakov.weather.utils.convertWeatherToEntity


class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {

    override fun saveEntity(weather: Weather) =
        localDataSource.insert(convertWeatherToEntity(weather))

    override fun getAllHistory() = convertHistoryEntityToWeather(localDataSource.all())
}
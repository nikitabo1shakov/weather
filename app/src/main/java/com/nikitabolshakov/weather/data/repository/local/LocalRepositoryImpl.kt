package com.nikitabolshakov.weather.data.repository.local

import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.data.room.HistoryDao
import com.nikitabolshakov.weather.utils.data.convertHistoryEntityToWeather
import com.nikitabolshakov.weather.utils.data.convertWeatherToEntity


class LocalRepositoryImpl(
    private val localDataSource: HistoryDao
) : LocalRepository {

    override fun saveEntity(weather: Weather) =
        localDataSource.insert(convertWeatherToEntity(weather))

    override fun getAllHistory() = convertHistoryEntityToWeather(localDataSource.all())
}
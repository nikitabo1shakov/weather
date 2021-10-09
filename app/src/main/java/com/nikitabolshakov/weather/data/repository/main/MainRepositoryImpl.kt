package com.nikitabolshakov.weather.data.repository.main

import com.nikitabolshakov.weather.data.models.local.Weather
import com.nikitabolshakov.weather.data.models.local.getRussianCities
import com.nikitabolshakov.weather.data.models.local.getWorldCities

class MainRepositoryImpl : MainRepository {

    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
package com.nikitabolshakov.weather.model.repository.main

import com.nikitabolshakov.weather.model.data.Weather
import com.nikitabolshakov.weather.model.data.getRussianCities
import com.nikitabolshakov.weather.model.data.getWorldCities

class MainRepositoryImpl : MainRepository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
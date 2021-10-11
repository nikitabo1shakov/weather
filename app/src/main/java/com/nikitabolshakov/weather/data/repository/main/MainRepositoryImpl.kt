package com.nikitabolshakov.weather.data.repository.main

import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.data.model.getRussianCities
import com.nikitabolshakov.weather.data.model.getWorldCities

class MainRepositoryImpl : MainRepository {

    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}
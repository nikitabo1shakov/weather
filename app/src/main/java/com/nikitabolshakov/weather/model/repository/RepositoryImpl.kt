package com.nikitabolshakov.weather.model.repository

import com.nikitabolshakov.weather.model.data.Weather
import com.nikitabolshakov.weather.model.data.getRussianCities
import com.nikitabolshakov.weather.model.data.getWorldCities

class RepositoryImpl : Repository {

    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }

}
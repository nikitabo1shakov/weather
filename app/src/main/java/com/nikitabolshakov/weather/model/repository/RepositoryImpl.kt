package com.nikitabolshakov.weather.model.repository

import com.nikitabolshakov.weather.model.data.Weather

class RepositoryImpl : Repository {

    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }
}
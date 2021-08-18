package com.nikitabolshakov.weather.model.utils

import com.nikitabolshakov.weather.model.data.City
import com.nikitabolshakov.weather.model.data.Weather
import com.nikitabolshakov.weather.model.data.getDefaultCity
import com.nikitabolshakov.weather.model.dto.WeatherDTO
import com.nikitabolshakov.weather.room.HistoryEntity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact = weatherDTO.fact!!
    return listOf(
        Weather(
            getDefaultCity(),
            fact.temp!!,
            fact.feels_like!!,
            fact.condition!!
        )
    )
}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
    }
}

fun convertWeatherToEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(0, weather.city.city, weather.temperature, weather.condition)
}
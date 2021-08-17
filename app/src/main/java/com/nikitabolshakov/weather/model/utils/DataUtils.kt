package com.nikitabolshakov.weather.model.utils

import com.nikitabolshakov.weather.model.data.Weather
import com.nikitabolshakov.weather.model.data.getDefaultCity
import com.nikitabolshakov.weather.model.dto.WeatherDTO

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
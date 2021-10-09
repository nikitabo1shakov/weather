package com.nikitabolshakov.weather.data.models.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val condition: String = "sunny"
) : Parcelable

fun getDefaultCity() = City("Москва", 55.5578, 37.61729)
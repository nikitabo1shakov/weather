package com.nikitabolshakov.weather.utils.cicerone

import com.github.terrakok.cicerone.Screen

interface IScreenOpener {
    fun openHomeFragment(): Screen
    fun openHistoryFragment(): Screen
    fun openGoogleMapsFragment(): Screen
}
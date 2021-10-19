package com.nikitabolshakov.weather.presentation.utils.screen_opener

import com.github.terrakok.cicerone.Screen

interface IScreenOpener {
    fun openHomeFragment(): Screen
    fun openHistoryFragment(): Screen
    fun openGoogleMapsFragment(): Screen
}
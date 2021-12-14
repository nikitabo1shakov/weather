package com.nikitabolshakov.weather.utils.cicerone

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nikitabolshakov.weather.presentation.view.fragments.googleMaps.GoogleMapsFragment
import com.nikitabolshakov.weather.presentation.view.fragments.history.HistoryFragment
import com.nikitabolshakov.weather.presentation.view.fragments.home.HomeFragment

class ScreenOpener : IScreenOpener {
    override fun openHomeFragment() = FragmentScreen { HomeFragment() }
    override fun openHistoryFragment() = FragmentScreen { HistoryFragment() }
    override fun openGoogleMapsFragment() = FragmentScreen { GoogleMapsFragment() }
}
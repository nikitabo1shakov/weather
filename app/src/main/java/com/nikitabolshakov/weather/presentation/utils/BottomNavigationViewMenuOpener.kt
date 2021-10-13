package com.nikitabolshakov.weather.presentation.utils

import androidx.fragment.app.FragmentManager
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.presentation.view.fragment.home.HomeFragment
import com.nikitabolshakov.weather.presentation.view.fragment.googlemaps.GoogleMapsFragment
import com.nikitabolshakov.weather.presentation.view.fragment.history.HistoryFragment

class BottomNavigationViewMenuOpener(
    private val fragmentManager: FragmentManager
) {

    fun openHomeFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }

    fun openHistoryFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, HistoryFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }

    fun openGoogleMapsFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, GoogleMapsFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }
}
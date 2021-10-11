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
            .replace(R.id.container_main_activity, HomeFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }

    fun openHistoryFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container_main_activity, HistoryFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }

    fun openGoogleMapsFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container_main_activity, GoogleMapsFragment())
            .addToBackStack("")
            .commitAllowingStateLoss()
    }
}
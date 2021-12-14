package com.nikitabolshakov.weather.experimental.bottomNavigationViewMenuOpener

import androidx.fragment.app.FragmentManager
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.presentation.view.fragments.home.HomeFragment
import com.nikitabolshakov.weather.presentation.view.fragments.googleMaps.GoogleMapsFragment
import com.nikitabolshakov.weather.presentation.view.fragments.history.HistoryFragment

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
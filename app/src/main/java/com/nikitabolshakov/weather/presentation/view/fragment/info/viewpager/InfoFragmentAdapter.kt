package com.nikitabolshakov.weather.presentation.view.fragment.info.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nikitabolshakov.weather.presentation.view.fragment.info.screen.*

private const val SCREEN_CITY_LIST_FRAGMENT = 0
private const val SCREEN_HISTORY_FRAGMENT = 1
private const val SCREEN_GOOGLE_MAPS_FRAGMENT = 2
private const val SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT = 3
private const val SCREEN_CHANGE_CITY_LIST_FRAGMENT = 4

class InfoFragmentAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        ScreenCityListFragment(),
        ScreenHistoryFragment(),
        ScreenGoogleMapsFragment(),
        ScreenLocationSearchingAndGetWeatherFragment(),
        ScreenChangeCityListFragment()
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[SCREEN_CITY_LIST_FRAGMENT]
            1 -> fragments[SCREEN_HISTORY_FRAGMENT]
            2 -> fragments[SCREEN_GOOGLE_MAPS_FRAGMENT]
            3 -> fragments[SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT]
            4 -> fragments[SCREEN_CHANGE_CITY_LIST_FRAGMENT]
            else -> fragments[SCREEN_CITY_LIST_FRAGMENT]
        }
    }

    override fun getCount() = fragments.size
}
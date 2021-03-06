package com.nikitabolshakov.weather.presentation.adapters.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_CHANGE_CITY_LIST_FRAGMENT
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_HOME_FRAGMENT
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_GOOGLE_MAPS_FRAGMENT
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_HISTORY_FRAGMENT
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT
import com.nikitabolshakov.weather.utils.data.ViewPagerFragmentsConstant.SCREEN_VISIBILITY_FRAGMENT
import com.nikitabolshakov.weather.presentation.view.fragments.info.screens.*

class InfoFragmentAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayOf(
        ScreenHomeFragment(),
        ScreenHistoryFragment(),
        ScreenGoogleMapsFragment(),
        ScreenLocationSearchingAndGetWeatherFragment(),
        ScreenChangeCityListFragment(),
        ScreenVisibilityFragment()
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[SCREEN_HOME_FRAGMENT]
            1 -> fragments[SCREEN_HISTORY_FRAGMENT]
            2 -> fragments[SCREEN_GOOGLE_MAPS_FRAGMENT]
            3 -> fragments[SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT]
            4 -> fragments[SCREEN_CHANGE_CITY_LIST_FRAGMENT]
            5 -> fragments[SCREEN_VISIBILITY_FRAGMENT]
            else -> fragments[SCREEN_HOME_FRAGMENT]
        }
    }

    override fun getCount() = fragments.size
}
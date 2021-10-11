package com.nikitabolshakov.weather.presentation.view.fragment.info.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.nikitabolshakov.weather.databinding.FragmentInfoBinding
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_CHANGE_CITY_LIST_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_HOME_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_GOOGLE_MAPS_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_HISTORY_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ViewPagerFragmentsConstant.SCREEN_SETTINGS_FRAGMENT
import com.nikitabolshakov.weather.presentation.utils.ZoomOutPageTransformer
import me.relex.circleindicator.CircleIndicator

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.infoViewPager.adapter = InfoFragmentAdapter(childFragmentManager)

        val infoViewPager: ViewPager = binding.infoViewPager
        val indicator: CircleIndicator = binding.indicator

        infoViewPager.adapter
        indicator.setViewPager(infoViewPager)
        indicator.createIndicators(6, 0)

        infoViewPager.setPageTransformer(true, ZoomOutPageTransformer())

        binding.infoViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                when (position) {
                    SCREEN_HOME_FRAGMENT -> indicator.animatePageSelected(0)
                    SCREEN_HISTORY_FRAGMENT -> indicator.animatePageSelected(1)
                    SCREEN_GOOGLE_MAPS_FRAGMENT -> indicator.animatePageSelected(2)
                    SCREEN_SETTINGS_FRAGMENT -> indicator.animatePageSelected(3)
                    SCREEN_LOCATION_SEARCHING_AND_GET_WEATHER_FRAGMENT -> indicator.animatePageSelected(
                        4
                    )
                    SCREEN_CHANGE_CITY_LIST_FRAGMENT -> indicator.animatePageSelected(5)
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
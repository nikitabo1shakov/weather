package com.nikitabolshakov.weather.presentation.view.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.data.model.City
import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.databinding.FragmentWeatherBinding
import com.nikitabolshakov.weather.data.model.appState.AppState
import com.nikitabolshakov.weather.utils.ui.makeGone
import com.nikitabolshakov.weather.utils.ui.makeVisible
import com.nikitabolshakov.weather.utils.ui.showSnackBar
import com.nikitabolshakov.weather.presentation.viewModels.weather.WeatherViewModel
import com.squareup.picasso.Picasso

class WeatherFragment : Fragment() {

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): WeatherFragment {
            val fragment = WeatherFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var weatherBundle: Weather

    private val weatherViewModel: WeatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()

        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) { renderData(it) }
        weatherViewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.weatherFragment.makeVisible()
                binding.includeProgressBarLayout.progressBarLayout.makeGone()
                setWeather(appState.weatherData.first())
            }
            is AppState.Loading -> {
                binding.weatherFragment.makeGone()
                binding.includeProgressBarLayout.progressBarLayout.makeVisible()
            }
            is AppState.Error -> {
                binding.weatherFragment.makeVisible()
                binding.includeProgressBarLayout.progressBarLayout.makeGone()
                binding.weatherFragment.showSnackBar(
                    getString(R.string.text_error),
                    getString(R.string.text_reload)
                ) {
                    weatherViewModel.getWeatherFromRemoteSource(
                        weatherBundle.city.lat,
                        weatherBundle.city.lon
                    )
                }
            }
        }
    }

    private fun setWeather(weather: Weather) {
        with(binding) {
            weatherBundle.city.let { city ->
                cityNameTextView.text = city.city
                cityCoordinatesTextView.text = String.format(
                    getString(R.string.city_coordinates),
                    city.lat.toString(),
                    city.lon.toString()
                )
                saveCity(city, weather)
            }
            weather.let {
                temperatureValueTextView.text = it.temperature.toString()
                feelsLikeValueTextView.text = it.feelsLike.toString()
                weatherConditionTextView.text = it.condition
            }
            Picasso
                .get()
                .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                .into(headerIcon)
        }
    }

    private fun saveCity(
        city: City,
        weather: Weather
    ) {
        weatherViewModel.saveCityToDB(
            Weather(
                city,
                weather.temperature,
                weather.feelsLike,
                weather.condition
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
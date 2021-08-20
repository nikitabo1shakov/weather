package com.nikitabolshakov.weather.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.FragmentMainBinding
import com.nikitabolshakov.weather.model.utils.hide
import com.nikitabolshakov.weather.model.utils.show
import com.nikitabolshakov.weather.model.utils.showSnackBar
import com.nikitabolshakov.weather.view.details.DetailsFragment
import com.nikitabolshakov.weather.viewmodel.AppState
import com.nikitabolshakov.weather.viewmodel.MainViewModel

private const val IS_RUSSIAN_KEY = "LIST_OF_RUSSIAN_KEY"

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = MainFragmentAdapter()
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener { weather ->
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }

        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener {
            changeWeatherDataSet()
            saveListOfTowns()
        }
        val observer = Observer<AppState> { renderData(it) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        loadListOfTowns()
        showWeatherDataSet()
    }

    private fun loadListOfTowns() {
        requireActivity().apply {
            isDataSetRus = getPreferences(Context.MODE_PRIVATE).getBoolean(IS_RUSSIAN_KEY, true)
        }
    }

    private fun saveListOfTowns() {
        requireActivity().apply {
            getPreferences(Context.MODE_PRIVATE).edit {
                putBoolean(IS_RUSSIAN_KEY, isDataSetRus)
                apply()
            }
        }
    }

    private fun changeWeatherDataSet() {
        isDataSetRus = !isDataSetRus
        showWeatherDataSet()
    }

    private fun showWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.includedLoadingLayout.loadingLayout.hide()
                adapter.setWeather(data.weatherData)
            }
            is AppState.Loading -> {
                binding.includedLoadingLayout.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.includedLoadingLayout.loadingLayout.hide()
                binding.mainFragmentFAB.showSnackBar(
                    getString(R.string.text_error),
                    getString(R.string.text_reload)
                ) {
                    if (isDataSetRus) {
                        viewModel.getWeatherFromLocalSourceRus()
                    } else {
                        viewModel.getWeatherFromLocalSourceWorld()
                    }
                }
            }
        }
    }
}
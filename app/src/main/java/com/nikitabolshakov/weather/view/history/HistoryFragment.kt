package com.nikitabolshakov.weather.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.FragmentHistoryBinding
import com.nikitabolshakov.weather.model.utils.hide
import com.nikitabolshakov.weather.model.utils.show
import com.nikitabolshakov.weather.model.utils.showSnackBar
import com.nikitabolshakov.weather.viewmodel.AppState
import com.nikitabolshakov.weather.viewmodel.history.HistoryViewModel

class HistoryFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private val adapter: HistoryFragmentAdapter by lazy {
        HistoryFragmentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyFragmentRecyclerview.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.historyFragmentRecyclerview.show()
                binding.includedLoadingLayout.loadingLayout.hide()
                adapter.setData(appState.weatherData)
            }
            is AppState.Loading -> {
                binding.historyFragmentRecyclerview.hide()
                binding.includedLoadingLayout.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.historyFragmentRecyclerview.show()
                binding.includedLoadingLayout.loadingLayout.hide()
                binding.historyFragmentRecyclerview.showSnackBar(
                    getString(R.string.text_error),
                    getString(R.string.text_reload)
                ) {
                    viewModel.getAllHistory()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
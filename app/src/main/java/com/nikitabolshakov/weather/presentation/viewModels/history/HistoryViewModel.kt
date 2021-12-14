package com.nikitabolshakov.weather.presentation.viewModels.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.application.App.Companion.getHistoryDao
import com.nikitabolshakov.weather.data.repository.local.LocalRepository
import com.nikitabolshakov.weather.data.repository.local.LocalRepositoryImpl
import com.nikitabolshakov.weather.data.model.appState.AppState

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}
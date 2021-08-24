package com.nikitabolshakov.weather.viewmodel.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.app.App.Companion.getHistoryDao
import com.nikitabolshakov.weather.model.repository.local.LocalRepository
import com.nikitabolshakov.weather.model.repository.local.LocalRepositoryImpl
import com.nikitabolshakov.weather.viewmodel.AppState

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}
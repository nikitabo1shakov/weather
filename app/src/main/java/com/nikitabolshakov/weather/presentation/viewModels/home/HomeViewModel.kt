package com.nikitabolshakov.weather.presentation.viewModels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.data.repository.main.MainRepository
import com.nikitabolshakov.weather.data.repository.main.MainRepositoryImpl
import com.nikitabolshakov.weather.data.model.appState.AppState
import java.lang.Thread.sleep

class HomeViewModel(
    private val mainRepository: MainRepository = MainRepositoryImpl()
) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussia = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussia = false)

    private fun getDataFromLocalSource(isRussia: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(3000)
            liveDataToObserve.postValue(
                AppState.Success(
                    when (isRussia) {
                        true -> mainRepository.getWeatherFromLocalStorageRus()
                        false -> mainRepository.getWeatherFromLocalStorageWorld()
                    }
                )
            )
        }.start()
    }
}
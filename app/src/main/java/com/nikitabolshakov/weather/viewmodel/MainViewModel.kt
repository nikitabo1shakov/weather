package com.nikitabolshakov.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.model.repository.main.MainRepository
import com.nikitabolshakov.weather.model.repository.main.MainRepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val mainRepository: MainRepository = MainRepositoryImpl()) :
    ViewModel() {

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
                    if (isRussia) mainRepository.getWeatherFromLocalStorageRus()
                    else mainRepository.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}
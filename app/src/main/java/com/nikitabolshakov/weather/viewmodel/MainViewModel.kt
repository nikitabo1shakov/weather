package com.nikitabolshakov.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.model.AppState
import com.nikitabolshakov.weather.model.repository.Repository
import com.nikitabolshakov.weather.model.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    private var counter: Int = 0

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getWeatherFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            counter++
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
        }.start()
    }

    fun getWeatherFromRemoteSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(2000)
            counter++
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromServer()))
        }.start()
    }
}
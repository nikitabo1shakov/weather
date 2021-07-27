package com.nikitabolshakov.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.model.AppState
import java.lang.Thread.sleep

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    private var counter: Int = 0

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun requestData(data: String) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(2000)
            counter++
            liveDataToObserve.postValue(AppState.Success(data + counter))
        }.start()
    }
}
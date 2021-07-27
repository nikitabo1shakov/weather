package com.nikitabolshakov.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<String> = MutableLiveData()

    private var counter: Int = 0

    fun getData(): LiveData<String> {
        return liveDataToObserve
    }

    fun requestData(data: String) {
        Thread {
            sleep(2000)
            counter++
            liveDataToObserve.postValue(data + counter)
        }.start()
    }
}
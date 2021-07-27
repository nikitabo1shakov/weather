package com.nikitabolshakov.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) : ViewModel() {

    fun getData(): LiveData<Any> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        Thread {
            sleep(2000)
            liveDataToObserve.postValue(Any())
        }.start()
    }
}
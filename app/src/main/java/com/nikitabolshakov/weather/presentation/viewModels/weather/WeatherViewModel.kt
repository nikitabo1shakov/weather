package com.nikitabolshakov.weather.presentation.viewModels.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.application.App.Companion.getHistoryDao
import com.nikitabolshakov.weather.data.model.Weather
import com.nikitabolshakov.weather.data.model.response.FactDTO
import com.nikitabolshakov.weather.data.model.response.WeatherDTO
import com.nikitabolshakov.weather.data.repository.local.LocalRepository
import com.nikitabolshakov.weather.data.repository.local.LocalRepositoryImpl
import com.nikitabolshakov.weather.data.repository.remote.RemoteDataSource
import com.nikitabolshakov.weather.data.repository.weather.WeatherRepository
import com.nikitabolshakov.weather.data.repository.weather.WeatherRepositoryImpl
import com.nikitabolshakov.weather.utils.data.convertDtoToModel
import com.nikitabolshakov.weather.data.model.appState.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable

private const val SERVER_ERROR = "Ошибка сервера"
private const val CORRUPTED_DATA = "Неполные данные"

class WeatherViewModel(
    val weatherLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    private val disposable = CompositeDisposable()

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        weatherLiveData.value = AppState.Loading
        disposable.add(
            weatherRepository.getWeatherDetailsFromServer(lat, lon)
                .subscribe(
                    { weatherLiveData.postValue(checkResponse(it)) },
                    { weatherLiveData.postValue(AppState.Error(Throwable(SERVER_ERROR))) }
                ))
    }

    fun saveCityToDB(weather: Weather) {
        historyRepository.saveEntity(weather)
    }

    private fun checkResponse(serverResponse: WeatherDTO): AppState {
        val fact: FactDTO? = serverResponse.fact
        return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
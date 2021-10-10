package com.nikitabolshakov.weather.presentation.viewmodel.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.weather.data.utils.convertDtoToModel
import com.nikitabolshakov.weather.data.app.App.Companion.getHistoryDao
import com.nikitabolshakov.weather.data.model.response.FactDTO
import com.nikitabolshakov.weather.data.model.response.WeatherDTO
import com.nikitabolshakov.weather.data.model.local.Weather
import com.nikitabolshakov.weather.data.repository.remote.RemoteDataSource
import com.nikitabolshakov.weather.data.repository.weather.WeatherRepository
import com.nikitabolshakov.weather.data.repository.weather.WeatherRepositoryImpl
import com.nikitabolshakov.weather.data.repository.local.LocalRepository
import com.nikitabolshakov.weather.data.repository.local.LocalRepositoryImpl
import com.nikitabolshakov.weather.presentation.state.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class WeatherViewModel(
    val weatherLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        weatherLiveData.value = AppState.Loading
        weatherRepository.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    fun saveCityToDB(weather: Weather) {
        historyRepository.saveEntity(weather)
    }

    private val callBack = object : Callback<WeatherDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            val serverResponse: WeatherDTO? = response.body()
            weatherLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            weatherLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun checkResponse(serverResponse: WeatherDTO): AppState {
        val fact: FactDTO? = serverResponse.fact
        return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}
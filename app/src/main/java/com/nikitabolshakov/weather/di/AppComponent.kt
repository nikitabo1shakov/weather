package com.nikitabolshakov.weather.di

import com.nikitabolshakov.weather.presentation.view.activity.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
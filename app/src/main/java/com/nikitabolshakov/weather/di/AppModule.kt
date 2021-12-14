package com.nikitabolshakov.weather.di

import com.nikitabolshakov.weather.application.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}
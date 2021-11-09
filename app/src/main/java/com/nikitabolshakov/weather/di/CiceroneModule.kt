package com.nikitabolshakov.weather.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.weather.presentation.utils.screen_opener.IScreenOpener
import com.nikitabolshakov.weather.presentation.utils.screen_opener.ScreenOpener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun router(): Router = cicerone.router

    @Provides
    @Singleton
    fun screenOpener(): IScreenOpener = ScreenOpener()
}
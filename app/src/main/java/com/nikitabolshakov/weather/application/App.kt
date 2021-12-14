package com.nikitabolshakov.weather.application

import android.app.Application
import androidx.room.Room
import com.nikitabolshakov.weather.data.room.HistoryDao
import com.nikitabolshakov.weather.data.room.HistoryDataBase
import com.nikitabolshakov.weather.di.AppComponent
import com.nikitabolshakov.weather.di.AppModule
import com.nikitabolshakov.weather.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {

        var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) {
                            throw IllegalStateException("Application is null while creating DataBase")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
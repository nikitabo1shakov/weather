package com.nikitabolshakov.weather.app

import android.app.Application
import androidx.room.Room
import com.nikitabolshakov.weather.room.HistoryDao
import com.nikitabolshakov.weather.room.HistoryDataBase
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
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
}
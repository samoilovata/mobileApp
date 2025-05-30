package com.example.helloworld

import android.app.Application
import androidx.room.Room
import com.example.helloworld.db.DatabaseApp

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    val db: DatabaseApp by lazy {
        Room.databaseBuilder(
            this,
            DatabaseApp::class.java,
            "database"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
}
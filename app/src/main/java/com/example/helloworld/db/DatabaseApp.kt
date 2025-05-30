package com.example.helloworld.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Activity::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
}
package com.example.helloworld.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    fun getAllActivity(): LiveData<List<Activity>>

    @Query("SELECT * FROM activity WHERE id = :id")
    fun getActivityByID(id: Int): LiveData<Activity>

    @Query("DELETE FROM activity WHERE id = :id")
    fun delete(id: Int)

    @Insert
    suspend fun insert(activity: Activity)
}
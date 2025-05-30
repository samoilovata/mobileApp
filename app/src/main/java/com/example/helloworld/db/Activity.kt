package com.example.helloworld.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "activity")
data class Activity (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "distance") val distance: Int,
    @ColumnInfo(name = "timeSpentHours") val timeSpentHours: Int,
    @ColumnInfo(name = "timeSpentMinutes") val timeSpentMinutes: Int,
    @ColumnInfo(name = "timeStart") val timeStart: String,
    @ColumnInfo(name = "timeFinish") val timeFinish: String,
    @ColumnInfo(name = "activityType") val activityType: String,
    @ColumnInfo(name = "comment") val comment: String?
)
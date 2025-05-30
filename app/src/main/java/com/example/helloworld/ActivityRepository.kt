package com.example.helloworld

import com.example.helloworld.db.Activity


class ActivityRepository {

    private val defaultActivityList1 = mutableListOf(
        Activity(
            null,
            "",
            "04.05.2025",
            0,
            0,
            0,
            "",
            "",
            "",
            null,
        ),
        Activity(
            3,
            "fancy_vibes",
            "29.05.2022",
            200,
            0,
            60,
            "Велосипед",
            "14 часов назад",
            "14:49",
            "Standing here I realize",
        )
    )

    public fun getActivityUsers() : MutableList<Activity> {
        return defaultActivityList1
    }
}
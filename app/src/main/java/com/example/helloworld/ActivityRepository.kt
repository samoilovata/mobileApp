package com.example.helloworld

import ActivityInfo

class ActivityRepository {

    private val defaultActivityList = listOf(
        ActivityInfo(
            null,
            null,
            "04.05.2025",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        ActivityInfo(
            0,
            "jesus",
            "04.05.2025",
            1000.0,
            2,
            40,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "Воскрес"
        ),
        ActivityInfo(
            null,
            null,
            "29.05.2022",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        ActivityInfo(
            1,
            "hsiurg",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        ),
        ActivityInfo(
            2,
            "myProfile",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        ),
        ActivityInfo(
            3,
            "cbcbcbcbcbcb",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        ),
        ActivityInfo(
            null,
            null,
            "29.05.2022",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        ActivityInfo(
            3,
            "cbcbcbcbcbcb",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        ),
        ActivityInfo(
            4,
            "fhsiurg",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        ),
    )

    private val defaultActivityList1 = listOf(
        ActivityInfo(
            null,
            null,
            "04.05.2025",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ),
        ActivityInfo(
            3,
            "cbcbcbcbcbcb",
            "29.05.2022",
            200.0,
            0,
            60,
            "Серфинг",
            "14 часов назад",
            "14:49",
            "16:31",
            "fgbishrgsik"
        )
    )

    public fun getActivityMine() : List<ActivityInfo> {
        return defaultActivityList
    }

    public fun getActivityUsers() : List<ActivityInfo> {
        return defaultActivityList1
    }
}
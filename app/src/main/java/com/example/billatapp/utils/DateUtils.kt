package com.example.billatapp.utils

import java.util.*

fun convertTravelTime(travelTimeString: String): String {
    var travelTime: Int = travelTimeString.toInt()
    var hours = 0
    while (travelTime >= 60) {
        hours++
        travelTime -= 60
    }
    val minutes: Int = travelTime
    val convertedTravelTime: String = if (minutes != 0) {
        "$hours h $minutes m"
    } else {
        "$hours h"
    }
    return convertedTravelTime
}

fun departureDateModification(departureDate: Date): String {
    val cal: Calendar = Calendar.getInstance()
    cal.time = departureDate
    val departureHour: String = if (cal.get(Calendar.HOUR_OF_DAY).toString().length < 2) {
        "0${cal.get(Calendar.HOUR_OF_DAY)}"
    } else {
        cal.get(Calendar.HOUR_OF_DAY).toString()
    }
    val departureMinute: String = if (cal.get(Calendar.MINUTE).toString().length < 2) {
        "${cal.get(Calendar.MINUTE)}0"
    } else {
        cal.get(Calendar.MINUTE).toString()
    }
    return "${departureHour}:${departureMinute}"
}
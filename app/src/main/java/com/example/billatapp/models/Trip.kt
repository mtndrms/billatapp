package com.example.billatapp.models

import java.util.*

data class Trip(
    val _id: String,
    val departureDate: Date,
    val arrivalDate: Date,
    val departureLocation: Station,
    val destinationLocation: Station,
    val travelTime: Int,
    val carrier: Carrier,
    val vehicle: Vehicle,
    val route: Route,
    val price: Int,
)

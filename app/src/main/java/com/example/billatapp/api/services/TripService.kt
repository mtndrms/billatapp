package com.example.billatapp.api.services

import com.example.billatapp.models.Trip
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TripService {
    @GET("trips/")
    fun getAllTrips(): Call<List<Trip>>

    @GET("trips/{tripId}")
    fun getTripById(@Path("tripId") tripId: String): Call<Trip>
}
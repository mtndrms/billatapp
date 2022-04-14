package com.example.billatapp.api.services

import com.example.billatapp.models.Carrier
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CarrierService {
    @GET("carriers/carrier?")
    fun getCarrierById(@Query("carrierId") carrierId: String): Call<Carrier>
}
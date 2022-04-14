package com.example.billatapp.api

import com.example.billatapp.constants.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BaseUrl.baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit as Retrofit
    }
}
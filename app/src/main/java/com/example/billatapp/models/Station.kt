package com.example.billatapp.models

data class Station(
    val _id: String,
    val city: String,
    val code: String,
    val plate: String,
    val name: String,
    val latitude: String,
    val longitude: String,
    val counties: List<String>,
)

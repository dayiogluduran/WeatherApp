package com.ddsoft.weatherapp.network.current_weather_dto


data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)
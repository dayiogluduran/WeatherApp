package com.ddsoft.weatherapp.network.current_weather_dto


data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
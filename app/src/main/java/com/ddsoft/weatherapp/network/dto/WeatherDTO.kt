package com.ddsoft.weather.network.dto

data class WeatherDTO(
    val id:Long,
    val main:String,
    val description:String,
    val icon:String
)
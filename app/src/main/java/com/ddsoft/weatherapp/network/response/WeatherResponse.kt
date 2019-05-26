package com.ddsoft.weather.network.response

import com.ddsoft.weather.network.dto.CityDTO
import com.ddsoft.weather.network.dto.ListDTO

data class WeatherResponse(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<ListDTO>,
    val city: CityDTO
)
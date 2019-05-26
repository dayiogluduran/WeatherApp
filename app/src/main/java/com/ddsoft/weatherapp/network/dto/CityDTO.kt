package com.ddsoft.weather.network.dto

data class CityDTO(
    val id: Long,
    val name: String,
    val coord: CoordDTO,
    val country: String
)
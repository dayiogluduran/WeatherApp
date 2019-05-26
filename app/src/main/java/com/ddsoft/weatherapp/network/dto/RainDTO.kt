package com.ddsoft.weather.network.dto

import com.google.gson.annotations.SerializedName

data class RainDTO(
    @SerializedName("3h")
    val ucH: Double
)
package com.ddsoft.weather.network.dto

data class ListDTO(
    val dt:Long,
    val main:MainDTO,
    val weather:List<WeatherDTO>,
    val clouds:CloudsDTO,
    val wind:WindDTO,
    val rain:RainDTO,
    val sys:SysDTO,
    val dt_txt:String
)
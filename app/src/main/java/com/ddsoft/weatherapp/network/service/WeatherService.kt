package com.ddsoft.weather.network.service

import androidx.lifecycle.LiveData
import com.ddsoft.weather.network.response.WeatherResponse
import com.ddsoft.weatherapp.network.response.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

////http://api.openweathermap.org/data/2.5/forecast/hourly?q=Istanbul&appid=0da3b8dc730ef0e62b9354003e2b8557
interface WeatherService {
    @GET("data/{apiVersion}/forecast/hourly")
    fun getWeatherByCityName(
        @Path("apiVersion") apiVersion:String,
        @Query("q") cityName: String,
        @Query("appid") appid: String,
        @Query("units") units:String
    ): Call<WeatherResponse>

   @GET("data/{apiVersion}/weather")
    fun getCurrentWeatherByCityName(
        @Path("apiVersion") apiVersion:String,
        @Query("q") cityName: String,
        @Query("appid") appid: String,
        @Query("units") units:String
    ): Call<CurrentWeatherResponse>
}
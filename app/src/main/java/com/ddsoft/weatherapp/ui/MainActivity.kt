package com.ddsoft.weatherapp.ui

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddsoft.weather.network.RetrofitClient
import com.ddsoft.weather.network.response.WeatherResponse
import com.ddsoft.weather.network.service.WeatherService
import com.ddsoft.weatherapp.network.response.CurrentWeatherResponse
import com.ddsoft.weatherapp.ui.adapter.FutureWeatherListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), retrofit2.Callback<CurrentWeatherResponse> {

    private var cityName: MutableLiveData<String> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ddsoft.weatherapp.R.layout.activity_main)

        cityName.value = "istanbul"

        var controller: LayoutAnimationController? = null

        btnSearch.setOnClickListener {
            if (txtCityName.text.isNotEmpty()) {
                cityName.value = txtCityName.text.toString()
                txtCityName.setText("")
                val klavye = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                klavye.hideSoftInputFromWindow(txtCityName.windowToken, 0)
            } else {
                Toast.makeText(this@MainActivity, "Lütfen şehir adı giriniz.", Toast.LENGTH_SHORT).show()
            }
        }

        btnRefresh.setOnClickListener {
            cityName.value = txtCurrentCityName.text.toString()
        }

        cityName.observe(this, Observer<String> { _cityName ->
            RetrofitClient.getClient()
                .create(WeatherService::class.java)
                .getCurrentWeatherByCityName("2.5", _cityName, "a915eae8a4291444a6d4b33ac5f15d78", "metric")
                .enqueue(this@MainActivity)

            RetrofitClient.getClient()
                .create(WeatherService::class.java)
                .getWeatherByCityName("2.5", _cityName, "a915eae8a4291444a6d4b33ac5f15d78", "metric")
                .enqueue(object : Callback<WeatherResponse> {
                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                        Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            controller = AnimationUtils.loadLayoutAnimation(
                                this@MainActivity,
                                com.ddsoft.weatherapp.R.anim.layout_fall_down
                            )
                            with(recycler_future_weather) {
                                layoutManager =
                                    LinearLayoutManager(this@MainActivity, LinearLayout.HORIZONTAL, false)
                                adapter = FutureWeatherListAdapter(ArrayList())
                                layoutAnimation = controller
                                scheduleLayoutAnimation()
                            }
                            (recycler_future_weather.adapter as? FutureWeatherListAdapter)?.setNewItem(response.body()?.list!!)
                        }
                    }
                })
        })
    }

    override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
        Toast.makeText(this@MainActivity, "Current Failure", Toast.LENGTH_SHORT).show()
        Log.e("Failure", t.message)
    }

    override fun onResponse(
        call: Call<CurrentWeatherResponse>,
        response: Response<CurrentWeatherResponse>
    ) {
        if (response.isSuccessful) {

            var currentWeatherState: String = ""

            when (response.body()?.weather?.get(0)?.icon) {
                "01d" -> {
                    currentWeatherState = "Hava Açık"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_acik)
                }
                "01n" -> {
                    currentWeatherState = "Hava Açık"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_acik)
                }
                "02n" -> {
                    currentWeatherState = "Az Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_azbulutlu)
                }
                "02d" -> {
                    currentWeatherState = "Az Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_azbulutlu)
                }
                "03d" -> {
                    currentWeatherState = "Parçalı Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_parcalibulutlu)
                }
                "03n" -> {
                    currentWeatherState = "Parçalı Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_parcalibulutlu)
                }
                "04d" -> {
                    currentWeatherState = "Parçalı Çok Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_cokbulutlu)
                }
                "04n" -> {
                    currentWeatherState = "Parçalı Çok Bulutlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_cokbulutlu)
                }
                "09d" -> {
                    currentWeatherState = "Sağanak Yağış"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_saganakyagisli)
                }
                "09n" -> {
                    currentWeatherState = "Sağanak Yağış"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_saganakyagisli)
                }
                "10d" -> {
                    currentWeatherState = "Yağmurlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_yagmurlu)
                }
                "10n" -> {
                    currentWeatherState = "Yağmurlu"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_yagmurlu)
                }
                "11d" -> {
                    currentWeatherState = "Gök Gürültülü Sağanak Yağışlı"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_gokgurultulu_saganak)
                }
                "114" -> {
                    currentWeatherState = "Gök Gürültülü Sağanak Yağışlı"
                    imgCurrentIcon.setBackgroundResource(com.ddsoft.weatherapp.R.drawable.ic_gokgurultulu_saganak)
                }
                "13d" -> {
                    currentWeatherState = "Karlı"
                }
                "13n" -> {
                    currentWeatherState = "Karlı"
                }
                "50n" -> {
                    currentWeatherState = "Sisli"
                }
                "50d" -> {
                    currentWeatherState = "Sisli"
                }
            }


            val netDate = Date(response.body()?.dt!! * 1000)
            val formattedDate = SimpleDateFormat("dd.MM.yyy / HH:mm")
            val time = formattedDate.format(netDate)

            var windSpeed = (response.body()?.wind?.speed)?.times(360)
            windSpeed = windSpeed?.rem(10000)
            windSpeed = windSpeed?.div(100)

            txtCurrentCityName.text = response.body()?.name.toString()
            txtCurrentTemp.text = "${response.body()?.main?.temp?.toInt()}°C"
            txtHumidity.text = "Nem: ${response.body()?.main?.humidity}%"
            txtCurrentState.text = currentWeatherState
            txtCurrentLastTime.text = "Güncelleme zamanı: $time"
            txtMinMaks.text =
                "${response.body()?.main?.tempMin?.toInt()}°/${response.body()?.main?.tempMax?.toInt()}°"
            txtWind.text = "${windSpeed?.toInt()} km/sa"

        } else {
            Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
        }
    }

}

fun String.getWeatherIcon(): String {
    return "http://openweathermap.org/img/w/$this.png"
}


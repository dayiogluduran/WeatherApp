package com.ddsoft.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddsoft.weather.network.dto.ListDTO
import com.ddsoft.weatherapp.R
import java.text.SimpleDateFormat
import java.util.*

class FutureWeatherListViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_item_future_list,
            parent,
            false
        )
    ) {
    private val imgStateIcon: ImageView
    private val txtTemp: TextView
    private val txtTime: TextView

    init {
        imgStateIcon = itemView.findViewById(R.id.img_future_icon)
        txtTemp = itemView.findViewById(R.id.txtFutureTempRec)
        txtTime = itemView.findViewById(R.id.txtFutureTimeRec)
    }

    fun bindTo(weatherResponse: ListDTO) {

            when (weatherResponse.weather.get(0).icon) {
                "01d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_acik)
                }
                "01n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_acik)
                }
                "02n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_azbulutlu)
                }
                "02d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_azbulutlu)
                }
                "03d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_parcalibulutlu)
                }
                "03n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_parcalibulutlu)
                }
                "04d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_cokbulutlu)
                }
                "04n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_cokbulutlu)
                }
                "09d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_saganakyagisli)
                }
                "09n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_saganakyagisli)
                }
                "10d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_yagmurlu)
                }
                "10n" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_yagmurlu)
                }
                "11d" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_gokgurultulu_saganak)
                }
                "114" -> {
                    imgStateIcon.setBackgroundResource(R.drawable.ic_gokgurultulu_saganak)
                }
                "13d" -> {
                }
                "13n" -> {
                }
                "50n" -> {
                }
                "50d" -> {
                }
            }

            val netDate = Date(weatherResponse.dt * 1000)
            val formattedDate = SimpleDateFormat("HH:mm")
            val time = formattedDate.format(netDate)

            txtTemp.setText("${weatherResponse.main?.temp.toInt()}°C")
            txtTime.setText(time.toString())

    }
}
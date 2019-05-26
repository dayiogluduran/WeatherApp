package com.ddsoft.weatherapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddsoft.weather.network.dto.ListDTO

class FutureWeatherListAdapter(weatherList: List<ListDTO>) : RecyclerView.Adapter<FutureWeatherListViewHolder>() {
    var weatherList = weatherList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureWeatherListViewHolder =
        FutureWeatherListViewHolder(parent)

    override fun getItemCount(): Int {
        weatherList.let {
            return it.size
        }
    }

    override fun onBindViewHolder(holder: FutureWeatherListViewHolder, position: Int) {
        weatherList?.let { holder.bindTo(weatherList[position]) }
    }

    fun setNewItem(weatherList: List<ListDTO>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }
}
package com.serhiymysyshyn.smartpethubapplication.logic.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour

class FeedingTimeAdapter internal constructor(dataList: List<FeedingHour>, dayOfWeek: Int) :
    RecyclerView.Adapter<FeedingTimeAdapter.ViewHolder>() {

    private var listOfHours: List<FeedingHour> = dataList
    private var dayOfWeek = dayOfWeek
    private lateinit var requiredContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.hour_info_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        requiredContext = PetsSmartHubApplication.getInstance()

        holder.hourField.text = listOfHours[position].feedingTimeHour
        holder.minuteField.text = listOfHours[position].feedingTimeMinute
        if (listOfHours[position].feedingType.equals("D")) {
            holder.foodType.background = requiredContext.getDrawable(R.drawable.baseline_feeding_24)
            holder.foodCapacity.text = listOfHours[position].foodCapacity.toString()
            holder.foodCapacityType.text = "g"
        }

        if (listOfHours[position].feedingType.equals("W")) {
            holder.foodType.background = requiredContext.getDrawable(R.drawable.baseline_water_drop_24)
            holder.foodCapacity.text = listOfHours[position].foodCapacity.toString()
            holder.foodCapacityType.text = "ml"
        }

        holder.deleteCurrentHourButton.setOnClickListener {
             //deleteScheduleItem(dayOfWeek, position)
        }
    }

    override fun getItemCount(): Int {
        return listOfHours.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hourField: TextView
        var minuteField: TextView
        var foodType: ImageView
        var foodCapacity: TextView
        var foodCapacityType: TextView
        var deleteCurrentHourButton: ImageButton

        init {
            hourField = itemView.findViewById(R.id.item_hour)
            minuteField = itemView.findViewById(R.id.item_minute)
            foodType = itemView.findViewById(R.id.imageView10)
            foodCapacity = itemView.findViewById(R.id.item_capacity)
            foodCapacityType = itemView.findViewById(R.id.item_capacity_v)
            deleteCurrentHourButton = itemView.findViewById(R.id.deleteCurrentTimeButton)
        }
    }
}
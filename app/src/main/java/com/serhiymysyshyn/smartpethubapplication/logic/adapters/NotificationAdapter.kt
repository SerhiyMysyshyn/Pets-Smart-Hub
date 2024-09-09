package com.serhiymysyshyn.smartpethubapplication.logic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import com.serhiymysyshyn.smartpethubapplication.logic.entities.AppNotification

class NotificationAdapter internal constructor() :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    var listOfNotifications: List<AppNotification> = arrayListOf()
    private var onClickListener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.type.text = listOfNotifications[position].type
        //holder.date.text = listOfNotifications[position].date
        holder.time.text = listOfNotifications[position].time

        if (listOfNotifications[position].imgRes == null) {
            PicassoHelper().loadDrawableToImageView(R.drawable.cat_and_error, holder.img)
        } else {
            PicassoHelper().loadDrawableToImageView(listOfNotifications[position].imgRes!!, holder.img)
        }

        holder.title.text = listOfNotifications[position].title
        holder.content.text = listOfNotifications[position].content

        holder.item.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, listOfNotifications[position] )
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfNotifications.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, appNotification: AppNotification)
    }

    fun updateList(newListOfNotifications: List<AppNotification>) {
        listOfNotifications = newListOfNotifications
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: CardView
        val type: TextView
        //val date: TextView
        val time: TextView
        val img: ImageView
        val title: TextView
        val content: TextView
        init {
            item = itemView.findViewById(R.id.notification_list_element)
            type = itemView.findViewById(R.id.notificationTitleType)
            //date = itemView.findViewById(R.id.sliderTitle)
            time = itemView.findViewById(R.id.notificationItemTime)
            img = itemView.findViewById(R.id.notificationItemImg)
            title = itemView.findViewById(R.id.notificationItemTitle)
            content = itemView.findViewById(R.id.notificationItemDetails)
        }
    }
}

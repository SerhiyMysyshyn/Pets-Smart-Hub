package com.serhiymysyshyn.smartpethubapplication.logic.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.cache.sharedPreferences.PrefsManager
import java.util.Arrays
import javax.inject.Inject

class NotificationSortAdapter(private val context: Context, _clickListener: OnItemClickListener) : RecyclerView.Adapter<NotificationSortAdapter.ViewHolder>() {
    var itemsList: MutableList<String> = Arrays.asList<String>(*context.resources.getStringArray(R.array.notificationSortValues))
    var clickListener: OnItemClickListener = _clickListener

    @Inject
    lateinit var sharedPreferences: PrefsManager

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        PetsSmartHubApplication.appComponent.inject(this@NotificationSortAdapter)

        if (position == sharedPreferences.getNotificationSortType()) {
            holder.itemSelector.visibility = View.VISIBLE
        } else {
            holder.itemSelector.visibility = View.INVISIBLE
        }

        holder.listItem.text = itemsList[position]
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var listItem: TextView = itemView.findViewById(R.id.simple_list_item)
        var itemSelector: ImageView = itemView.findViewById(R.id.simple_list_item_selector)

        init {
            itemView.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                clickListener?.onItemClick(adapterPosition)
            }
        }
    }
}
package com.serhiymysyshyn.smartpethubapplication.logic.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.logic.core.PicassoHelper
import java.util.Arrays

class GreetingPagerAdapter internal constructor(private val context: Context) :
    RecyclerView.Adapter<GreetingPagerAdapter.ViewHolder>() {
    var listOfImages: MutableList<Int> = ArrayList()
    var listOfTitles: List<String> = ArrayList()
    var listOfContent: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pager, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listOfImages.add(R.drawable.cat_dog_3)
        listOfImages.add(R.drawable.eating_dog_1)
        listOfImages.add(R.drawable.bot_1)
        listOfImages.add(R.drawable.cat_dog_1)

        listOfTitles = Arrays.asList<String>(*context.resources.getStringArray(R.array.greetingsTitleValues))
        listOfContent = Arrays.asList<String>(*context.resources.getStringArray(R.array.greetingsContentValues))

        //holder.image.setImageDrawable(listOfImages[position])
        PicassoHelper().loadDrawableToImageView(listOfImages[position], holder.image)
        holder.title.text = listOfTitles[position]
        holder.content.text = listOfContent[position]
    }

    override fun getItemCount(): Int {
        return 4
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var content: TextView
        init {
            image = itemView.findViewById(R.id.sliderImage)
            title = itemView.findViewById(R.id.sliderTitle)
            content = itemView.findViewById(R.id.sliderMainText)
        }
    }
}

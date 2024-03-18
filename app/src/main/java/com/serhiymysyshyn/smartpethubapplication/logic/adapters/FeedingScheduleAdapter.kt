package com.serhiymysyshyn.smartpethubapplication.logic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication
import com.serhiymysyshyn.smartpethubapplication.PetsSmartHubApplication.Companion.appComponent
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour
import com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule.SetFeedingTimeBottomSheetFragment
import javax.inject.Inject

class FeedingScheduleAdapter(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<FeedingScheduleAdapter.ViewHolder>() {

    @Inject
    lateinit var application: PetsSmartHubApplication

    private var hourList: List<FeedingHour> = ArrayList()

    init {
        appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_pager_feeding, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val l1 = ArrayList<FeedingHour>()
        val l2 = ArrayList<FeedingHour>()
        val l3 = ArrayList<FeedingHour>()
        val l4 = ArrayList<FeedingHour>()
        val l5 = ArrayList<FeedingHour>()
        val l6 = ArrayList<FeedingHour>()
        val l7 = ArrayList<FeedingHour>()

        for (item in hourList) {
            when (position) {
                // ViewPager 1 slide
                0 -> {
                    if (item.feedingType == "D") {
                        when(item.feedingDayOfWeek) {
                            1 -> l1.add(item)
                            2 -> l2.add(item)
                            3 -> l3.add(item)
                            4 -> l4.add(item)
                            5 -> l5.add(item)
                            6 -> l6.add(item)
                            7 -> l7.add(item)
                        }
                    }
                }
                // ViewPager 2 slide
                1 -> {
                    if (item.feedingType == "W") {
                        when(item.feedingDayOfWeek) {
                            1 -> l1.add(item)
                            2 -> l2.add(item)
                            3 -> l3.add(item)
                            4 -> l4.add(item)
                            5 -> l5.add(item)
                            6 -> l6.add(item)
                            7 -> l7.add(item)
                        }
                    }
                }
            }
        }

        val vlm1 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm2 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm3 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm4 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm5 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm6 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)
        val vlm7 = LinearLayoutManager(application.applicationContext, LinearLayoutManager.VERTICAL, false)

        holder.mondayRecyclerView.layoutManager = vlm1
        holder.tuesdayRecyclerView.layoutManager = vlm2
        holder.wednesdayRecyclerView.layoutManager = vlm3
        holder.thursdayRecyclerView.layoutManager = vlm4
        holder.fridayRecyclerView.layoutManager = vlm5
        holder.saturdayRecyclerView.layoutManager = vlm6
        holder.sundayRecyclerView.layoutManager = vlm7

        holder.mondayRecyclerViewSize.text = "(${l1.size})"
        holder.tuesdayRecyclerViewSize.text = "(${l2.size})"
        holder.wednesdayRecyclerViewSize.text = "(${l3.size})"
        holder.thursdayRecyclerViewSize.text = "(${l4.size})"
        holder.fridayRecyclerViewSize.text = "(${l5.size})"
        holder.saturdayRecyclerViewSize.text = "(${l6.size})"
        holder.sundayRecyclerViewSize.text = "(${l7.size})"

        holder.mondayRecyclerView.adapter = FeedingTimeAdapter(l1, 1)
        holder.tuesdayRecyclerView.adapter = FeedingTimeAdapter(l2, 2)
        holder.wednesdayRecyclerView.adapter = FeedingTimeAdapter(l3, 3)
        holder.thursdayRecyclerView.adapter = FeedingTimeAdapter(l4, 4)
        holder.fridayRecyclerView.adapter = FeedingTimeAdapter(l5, 5)
        holder.saturdayRecyclerView.adapter = FeedingTimeAdapter(l6, 6)
        holder.sundayRecyclerView.adapter = FeedingTimeAdapter(l7, 7)

        setClickListenersForDayElements(holder, position)

        holder.addFeedingTimeItemForMonday.setOnClickListener { showFeedingTimeBottomSheetDialog(1, position) }
        holder.addFeedingTimeItemForTuesday.setOnClickListener { showFeedingTimeBottomSheetDialog(2, position) }
        holder.addFeedingTimeItemForWednesday.setOnClickListener { showFeedingTimeBottomSheetDialog(3, position) }
        holder.addFeedingTimeItemForThursday.setOnClickListener { showFeedingTimeBottomSheetDialog(4, position) }
        holder.addFeedingTimeItemForFriday.setOnClickListener { showFeedingTimeBottomSheetDialog(5, position) }
        holder.addFeedingTimeItemForSaturday.setOnClickListener { showFeedingTimeBottomSheetDialog(6, position) }
        holder.addFeedingTimeItemForSunday.setOnClickListener { showFeedingTimeBottomSheetDialog(7, position) }
    }

    override fun getItemCount(): Int {
        return 2
    }

    fun updateRecyclerView(updatedHourList : List<FeedingHour>) {
        hourList = updatedHourList
        notifyDataSetChanged()
    }

    private fun showFeedingTimeBottomSheetDialog(dayOfWeek: Int, foodType: Int) {
        val bottomSheetFragment = SetFeedingTimeBottomSheetFragment.newInstance(dayOfWeek, foodType)
        bottomSheetFragment.show(fragmentManager, "FeedingTimeBottomSheetDialog")
    }

    private fun setClickListenersForDayElements(holder: ViewHolder, position: Int) {
        setListVisibilityClickListener(holder.mondayContainer, holder.mondayRecyclerView)
        setListVisibilityClickListener(holder.tuesdayContainer, holder.tuesdayRecyclerView)
        setListVisibilityClickListener(holder.wednesdayContainer, holder.wednesdayRecyclerView)
        setListVisibilityClickListener(holder.thursdayContainer, holder.thursdayRecyclerView)
        setListVisibilityClickListener(holder.fridayContainer, holder.fridayRecyclerView)
        setListVisibilityClickListener(holder.saturdayContainer, holder.saturdayRecyclerView)
        setListVisibilityClickListener(holder.sundayContainer, holder.sundayRecyclerView)
    }

    private fun setListVisibilityClickListener(rootView: View, listView: View) {
        rootView.setOnClickListener {
            if (listView.visibility == View.GONE) {
                listView.visibility = View.VISIBLE
            } else if (listView.visibility == View.VISIBLE) {
                listView.visibility = View.GONE
            }
        }
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mondayRecyclerView: RecyclerView
        var tuesdayRecyclerView: RecyclerView
        var wednesdayRecyclerView: RecyclerView
        var thursdayRecyclerView: RecyclerView
        var fridayRecyclerView: RecyclerView
        var saturdayRecyclerView: RecyclerView
        var sundayRecyclerView: RecyclerView

        var mondayRecyclerViewSize: TextView
        var tuesdayRecyclerViewSize: TextView
        var wednesdayRecyclerViewSize: TextView
        var thursdayRecyclerViewSize: TextView
        var fridayRecyclerViewSize: TextView
        var saturdayRecyclerViewSize: TextView
        var sundayRecyclerViewSize: TextView

        var mondayContainer: RelativeLayout
        var tuesdayContainer: RelativeLayout
        var wednesdayContainer: RelativeLayout
        var thursdayContainer: RelativeLayout
        var fridayContainer: RelativeLayout
        var saturdayContainer: RelativeLayout
        var sundayContainer: RelativeLayout

        var addFeedingTimeItemForMonday: ImageButton
        var addFeedingTimeItemForTuesday: ImageButton
        var addFeedingTimeItemForWednesday: ImageButton
        var addFeedingTimeItemForThursday: ImageButton
        var addFeedingTimeItemForFriday: ImageButton
        var addFeedingTimeItemForSaturday: ImageButton
        var addFeedingTimeItemForSunday: ImageButton

        init {
            mondayRecyclerView = itemView.findViewById(R.id.day1TimesRecyclerView)
            tuesdayRecyclerView = itemView.findViewById(R.id.day2TimesRecyclerView)
            wednesdayRecyclerView = itemView.findViewById(R.id.day3TimesRecyclerView)
            thursdayRecyclerView = itemView.findViewById(R.id.day4TimesRecyclerView)
            fridayRecyclerView = itemView.findViewById(R.id.day5TimesRecyclerView)
            saturdayRecyclerView = itemView.findViewById(R.id.day6TimesRecyclerView)
            sundayRecyclerView = itemView.findViewById(R.id.day7TimesRecyclerView)

            mondayRecyclerViewSize = itemView.findViewById(R.id.day1RegisteredItems)
            tuesdayRecyclerViewSize = itemView.findViewById(R.id.day2RegisteredItems)
            wednesdayRecyclerViewSize = itemView.findViewById(R.id.day3RegisteredItems)
            thursdayRecyclerViewSize = itemView.findViewById(R.id.day4RegisteredItems)
            fridayRecyclerViewSize = itemView.findViewById(R.id.day5RegisteredItems)
            saturdayRecyclerViewSize = itemView.findViewById(R.id.day6RegisteredItems)
            sundayRecyclerViewSize = itemView.findViewById(R.id.day7RegisteredItems)

            mondayContainer = itemView.findViewById(R.id.mondayContainer)
            tuesdayContainer = itemView.findViewById(R.id.tuesdayContainer)
            wednesdayContainer = itemView.findViewById(R.id.wednesdayContainer)
            thursdayContainer = itemView.findViewById(R.id.thursdayContainer)
            fridayContainer = itemView.findViewById(R.id.fridayContainer)
            saturdayContainer = itemView.findViewById(R.id.saturdayContainer)
            sundayContainer = itemView.findViewById(R.id.sundayContainer)

            addFeedingTimeItemForMonday = itemView.findViewById(R.id.day1AddButton)
            addFeedingTimeItemForTuesday = itemView.findViewById(R.id.day2AddButton)
            addFeedingTimeItemForWednesday = itemView.findViewById(R.id.day3AddButton)
            addFeedingTimeItemForThursday = itemView.findViewById(R.id.day4AddButton)
            addFeedingTimeItemForFriday = itemView.findViewById(R.id.day5AddButton)
            addFeedingTimeItemForSaturday = itemView.findViewById(R.id.day6AddButton)
            addFeedingTimeItemForSunday = itemView.findViewById(R.id.day7AddButton)
        }
    }
}
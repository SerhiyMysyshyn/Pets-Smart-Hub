package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.serhiymysyshyn.smartpethubapplication.databinding.FragmentSetFeedingTimeBottomSheetBinding
import com.serhiymysyshyn.smartpethubapplication.logic.entities.FeedingHour
import java.time.LocalDateTime

private const val ARG_PARAM1 = "dayOfWeekNumber"
private const val ARG_PARAM2 = "foodTypeNumber"

class SetFeedingTimeBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Int) =
            SetFeedingTimeBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }

        private var DEFAULT_FOOD_PORTION = 150
        private var DEFAULT_PORTION_MODE = 0
    }

    private lateinit var binding: FragmentSetFeedingTimeBottomSheetBinding
    private lateinit var viewModel: SetFeedingTimeBottomSheetViewModel
    private var _iFeedingSchedule: FeedingSchedule? = null
    private var param1: Int? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetFeedingTimeBottomSheetBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SetFeedingTimeBottomSheetViewModel::class.java)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FeedingSchedule) {
            _iFeedingSchedule = context
        } else {
            throw RuntimeException("$context must implement MyInterface")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.portionSizeV.apply {
            when (param2) {
                0 -> { this.text = "g" }
                1 -> { this.text = "ml" }
            }
        }

        binding.hourTimePicker.minValue = 0
        binding.hourTimePicker.maxValue = 23

        binding.minuteTimePicker.minValue = 0
        binding.minuteTimePicker.maxValue = 59

        binding.closeBottomSheetDialogButton.setOnClickListener {
            this@SetFeedingTimeBottomSheetFragment.dialog?.cancel()
        }

        binding.foodPortionTextView.text = DEFAULT_FOOD_PORTION.toString()

        binding.hourTimePicker.value = LocalDateTime.now().hour
        binding.minuteTimePicker.value = LocalDateTime.now().minute

        binding.minFoodPortionButton.setOnClickListener {
            viewModel.reduceFoodPortion(param2!!, DEFAULT_PORTION_MODE, getCurrentPortionValue().toInt())
        }

        binding.addFoodPortionButton.setOnClickListener {
            viewModel.increaseFoodPortion(param2!!, DEFAULT_PORTION_MODE, getCurrentPortionValue().toInt())
        }

        binding.saveNewScheduleButton.setOnClickListener {
            val newHourItem = FeedingHour(param1!!,
                                            binding.hourTimePicker.value.toString(),
                                            binding.minuteTimePicker.value.toString(),
                                            getFoodType(),
                                            getCurrentPortionValue().toInt())

            _iFeedingSchedule?.addNewScheduleItem(newHourItem)
            this@SetFeedingTimeBottomSheetFragment.dismiss()
        }

        viewModel.foodPortionValue.observe(viewLifecycleOwner) { newFoodPortion ->
            binding.foodPortionTextView.text = newFoodPortion.toString()
        }
    }

    private fun getFoodType(): String {
        when (param2) {
            0 -> { return "D" }
            1 -> { return "W" }
            else -> { return "D" }
        }
    }

    private fun getCurrentPortionValue() = binding.foodPortionTextView.text.toString()

    override fun onDetach() {
        super.onDetach()
        _iFeedingSchedule = null
    }

}
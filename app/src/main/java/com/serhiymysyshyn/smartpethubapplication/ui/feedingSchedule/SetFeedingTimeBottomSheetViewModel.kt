package com.serhiymysyshyn.smartpethubapplication.ui.feedingSchedule

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.serhiymysyshyn.smartpethubapplication.R
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.DRY_FOOD_DEFAULT_STEP
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.DRY_FOOD_MAX_PORTION_VALUE
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.DRY_FOOD_MIN_PORTION_VALUE
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.DRY_FOOD_SENSITIVE_STEP
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.WATER_DEFAULT_STEP
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.WATER_MAX_PORTION_VALUE
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.WATER_MIN_PORTION_VALUE
import com.serhiymysyshyn.smartpethubapplication.logic.core.AppConstants.Companion.WATER_SENSITIVE_STEP

class SetFeedingTimeBottomSheetViewModel(application: Application) : AndroidViewModel(application) {
    private val application = application
    val foodPortionValue: MutableLiveData<Int> = MutableLiveData()

    // mode parameters: 0 - default (+5 grams)
    //                  1 - sensitive (+1 gram)
    fun increaseFoodPortion(foodType: Int, mode: Int, value: Int) {
        var resValue = 0
        when (foodType) {
            0 -> {
                when (mode) {
                    0 -> {
                        resValue = value + DRY_FOOD_DEFAULT_STEP
                        if (resValue <= DRY_FOOD_MAX_PORTION_VALUE) {
                            foodPortionValue.value =resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.max_value_exceeded)}!\nMAX = $DRY_FOOD_MAX_PORTION_VALUE g",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                    1 -> {
                        resValue = value + DRY_FOOD_SENSITIVE_STEP
                        if (resValue <= DRY_FOOD_MAX_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.max_value_exceeded)}!\nMAX = $DRY_FOOD_MAX_PORTION_VALUE g",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                }
            }
            1 -> {
                when (mode) {
                    0 -> {
                        resValue = value + WATER_DEFAULT_STEP
                        if (resValue <= WATER_MAX_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.max_value_exceeded)}!\nMAX = $WATER_MAX_PORTION_VALUE ml",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                    1 -> {
                        resValue = value + WATER_SENSITIVE_STEP
                        if (resValue <= WATER_MAX_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.max_value_exceeded)}!\nMAX = $WATER_MAX_PORTION_VALUE ml",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                }
            }
        }
    }

    // mode parameters: 0 - default (-5 grams)
    //                  1 - sensitive (-1 gram)
    fun reduceFoodPortion(foodType: Int, mode: Int, value: Int) {
        var resValue = 0
        when (foodType) {
            0 -> {
                when (mode) {
                    0 -> {
                        resValue = value - DRY_FOOD_DEFAULT_STEP
                        if (resValue >= DRY_FOOD_MIN_PORTION_VALUE) {
                            foodPortionValue.value =  resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.min_value_exceeded)}!\nMIN = $DRY_FOOD_MIN_PORTION_VALUE g",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value =  value
                        }
                    }
                    1 -> {
                        resValue = value - DRY_FOOD_SENSITIVE_STEP
                        if (resValue >= DRY_FOOD_MIN_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.min_value_exceeded)}!\nMIN = $DRY_FOOD_MIN_PORTION_VALUE g",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value =  value
                        }
                    }
                }
            }
            1 -> {
                when (mode) {
                    0 -> {
                        resValue = value - WATER_DEFAULT_STEP
                        if (resValue >= WATER_MIN_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.min_value_exceeded)}!\nMIN = $WATER_MIN_PORTION_VALUE ml",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                    1 -> {
                        resValue = value - WATER_SENSITIVE_STEP
                        if (resValue >= WATER_MIN_PORTION_VALUE) {
                            foodPortionValue.value = resValue
                        } else {
                            Toast.makeText(application.baseContext,
                                "Warning! ${application.baseContext.resources.getString(R.string.min_value_exceeded)}!\nMIN = $WATER_MIN_PORTION_VALUE ml",
                                Toast.LENGTH_SHORT).show()
                            foodPortionValue.value = value
                        }
                    }
                }
            }
        }
    }

}
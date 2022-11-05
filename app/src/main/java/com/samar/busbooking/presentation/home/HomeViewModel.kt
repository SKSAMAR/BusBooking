package com.samar.busbooking.presentation.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.gson.Gson
import com.samar.busbooking.R
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.available.AvailableActivity
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.dates.OnDate
import com.samar.busbooking.util.preferences.BusPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val sharedPreferences: SharedPreferences) : BaseViewModel<Any>() {

    var dateModel by mutableStateOf(todayDates())
    var preferenceList = HashSet<BusPreferences>()
    val fromLocation = mutableStateOf<CityModel?>(null)
    val toLocation = mutableStateOf<CityModel?>(null)

    init {
        setupCities()
    }

    fun changeDate(onDate: OnDate) {
        when (onDate) {
            is OnDate.Today -> {
                dateModel = todayDates()
            }
            is OnDate.Tomorrow -> {
                tomorrowDates()
            }
            is OnDate.NextDay -> {
                nextDate()
            }
            is OnDate.PreviousDay -> {
                previousDate()
            }
        }

        getBatchList()
    }

    private fun todayDates(): DateModel {
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())

        val sdf2 = SimpleDateFormat("MMMM d")
        val englishDate = sdf2.format(Date())

        val sdf3 = SimpleDateFormat("EEEE")
        val day = sdf3.format(Date())

        val sdf4 = SimpleDateFormat("HH:mm:ss z")
        val time = sdf4.format(Date())
        return DateModel(
            classicDate = currentDate,
            englishData = englishDate,
            time = time,
            day = day,
            date = Date()
        )
    }

    private fun tomorrowDates() {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-M-dd")
        c.time = Date()
        c.add(Calendar.DATE, 1)
        val currentDate = sdf.format(c.time)


        val sdf2 = SimpleDateFormat("MMMM d")
        c.time = Date()
        c.add(Calendar.DATE, 1)
        val englishDate = sdf2.format(c.time)


        val sdf3 = SimpleDateFormat("EEEE")
        c.time = Date()
        c.add(Calendar.DATE, 1)
        val day = sdf3.format(c.time)


        val sdf4 = SimpleDateFormat("HH:mm:ss z")
        c.time = Date()
        c.add(Calendar.DATE, 1)
        val time = sdf4.format(c.time)
        dateModel = DateModel(
            classicDate = currentDate,
            englishData = englishDate,
            time = time,
            day = day,
            date = c.time
        )
    }

    private fun nextDate() {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-M-dd")
        c.time = dateModel.date
        c.add(Calendar.DATE, 1)
        val currentDate = sdf.format(c.time)


        val sdf2 = SimpleDateFormat("MMMM d")
        c.time = dateModel.date
        c.add(Calendar.DATE, 1)
        val englishDate = sdf2.format(c.time)


        val sdf3 = SimpleDateFormat("EEEE")
        c.time = dateModel.date
        c.add(Calendar.DATE, 1)
        val day = sdf3.format(c.time)


        val sdf4 = SimpleDateFormat("HH:mm:ss z")
        c.time = dateModel.date
        c.add(Calendar.DATE, 1)
        val time = sdf4.format(c.time)
        dateModel = DateModel(
            classicDate = currentDate,
            englishData = englishDate,
            time = time,
            day = day,
            date = c.time
        )
    }

    private fun previousDate() {
        val spotDate = Date()
        if (dateModel.date.before(spotDate)) {
            return
        }
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-M-dd")
        c.time = dateModel.date
        c.add(Calendar.DATE, -1)
        val currentDate = sdf.format(c.time)

        val sdf2 = SimpleDateFormat("MMMM d")
        c.time = dateModel.date
        c.add(Calendar.DATE, -1)
        val englishDate = sdf2.format(c.time)


        val sdf3 = SimpleDateFormat("EEEE")
        c.time = dateModel.date
        c.add(Calendar.DATE, -1)
        val day = sdf3.format(c.time)


        val sdf4 = SimpleDateFormat("HH:mm:ss z")
        c.time = dateModel.date
        c.add(Calendar.DATE, -1)
        val time = sdf4.format(c.time)
        dateModel = DateModel(
            classicDate = currentDate,
            englishData = englishDate,
            time = time,
            day = day,
            date = c.time
        )
    }

    fun toSpecificDates(date: Date) {
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(date)

        val sdf2 = SimpleDateFormat("MMMM d")
        val englishDate = sdf2.format(date)

        val sdf3 = SimpleDateFormat("EEEE")
        val day = sdf3.format(date)

        val sdf4 = SimpleDateFormat("HH:mm:ss z")
        val time = sdf4.format(date)
        dateModel = DateModel(
            classicDate = currentDate,
            englishData = englishDate,
            time = time,
            day = day,
            date = date
        )
    }

    fun getBatchList(): List<OnDate> {
        val c = Calendar.getInstance()
        val p = Calendar.getInstance()
        val currentDate = Date()
        c.add(Calendar.DATE, 1)
        val nextDay = c.time

        val previousDate = Date()
        p.add(Calendar.DATE, -1)
        val previousDay = c.time

        if (currentDate.date == dateModel.date.date && currentDate.day == dateModel.date.day) {
            Log.d("OnDateIs", "Current Date")
        } else if (nextDay.date == dateModel.date.date && nextDay.day == dateModel.date.day) {
            Log.d("OnDateIs", "Next Date")
        } else if (previousDay.date == dateModel.date.date && previousDay.day == dateModel.date.day) {
            Log.d("OnDateIs", "Next Date")
        } else {
            Log.d("OnDateIs", "Anther Date")
        }
        return emptyList()
    }

    fun addToPreference(preferences: BusPreferences) {
        if (preferenceList.contains(preferences)) {
            preferenceList.remove(preferences)
        } else {
            preferenceList.add(preferences)
        }
        preferenceList.forEach {
            Log.d("GivenPreferencesAre", it.preferenceModel.title)
        }
    }

    fun swapAddresses() {
        if (fromLocation.value != null || toLocation.value != null) {
            val temp = fromLocation.value
            fromLocation.value = toLocation.value
            toLocation.value = temp
        }
    }

    fun searchTheBus(context: Context) {
        if (fromLocation.value == null) {
            displayAnimation(R.raw.invalid, "Select a valid From Location", 2000)
        } else if (toLocation.value == null) {
            displayAnimation(R.raw.invalid, "Select a valid To Location", 2000)
        } else if ((toLocation.value?.id ?: "") == (fromLocation.value?.id ?: "")) {
            displayAnimation(R.raw.invalid, "Same Destination booking is not possible", 2000)
        } else {
            val intent = Intent(context, AvailableActivity::class.java)
            intent.putExtra(Constant.FromCityModel, fromLocation.value)
            intent.putExtra(Constant.ToCityModel, toLocation.value)
            intent.putExtra(Constant.Date, dateModel)
            context.startActivity(intent)
        }
    }

    private fun setupCities() {
        val toCity = sharedPreferences.getString(Constant.ToCityModel, null)
        val fromCity = sharedPreferences.getString(Constant.FromCityModel, null)
        toCity?.let {
            toLocation.value = Gson().fromJson(it, CityModel::class.java)
        }
        fromCity?.let {
            fromLocation.value = Gson().fromJson(it, CityModel::class.java)
        }
    }

    fun setToLocation(cityModel: CityModel) {
        val json = Gson().toJson(cityModel)
        json?.let {
            sharedPreferences.edit().putString(Constant.ToCityModel, it).apply()
        }
    }

    fun setFromLocation(cityModel: CityModel) {
        val json = Gson().toJson(cityModel)
        json?.let {
            sharedPreferences.edit().putString(Constant.FromCityModel, it).apply()
        }
    }

}
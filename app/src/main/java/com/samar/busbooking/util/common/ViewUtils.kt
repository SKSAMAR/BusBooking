package com.samar.busbooking.util.common

import android.annotation.SuppressLint
import android.content.Context
import android.net.ParseException
import android.widget.Toast
import com.samar.busbooking.domain.model.timing.Time
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.type.ClassType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONStringer
import java.text.SimpleDateFormat
import java.util.*


object ViewUtils {

    fun String.timeFromMinutesToActual(): Time? {
        try {
            var timeFormat = this.toInt() / 60 // Hours
            val reminder = this.toInt() % 60 // Minutes
            val journeyDay = timeFormat / 24 //Journey Of the day
            if (journeyDay>0){
                val times = journeyDay * 24
                timeFormat -= times
            }

            return Time(
                hours = timeFormat,
                minutes = reminder,
                journeyOfTheDay = journeyDay,
                twentyFourHoursTime = "$timeFormat hrs $reminder min",
                twelveHoursTime = twentyHoursToTwelveHours("$timeFormat:$reminder"),

                )
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: Throwable) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    @SuppressLint("SimpleDateFormat")
    private fun twentyHoursToTwelveHours(time: String): String {
        return try {
            val sdf = SimpleDateFormat("H:mm")
            val dateObj: Date = sdf.parse(time) as Date
            println(dateObj)
            SimpleDateFormat("K:mm a").format(dateObj)
        } catch (e: ParseException) {
            e.printStackTrace()
            e.localizedMessage ?: "some error"
        }
    }

    fun checkObjectOrArray(json: String, fieldName: String): ClassType {
        try {
            val dataObject = JSONObject(json)
            val json: Any = dataObject.get(fieldName)
            return if (json is JSONObject) {
                ClassType.OBJECT
            } else if (json is JSONArray) {
                ClassType.ARRAY
            } else if (json is JSONStringer) {
                ClassType.STRING
            } else{
                ClassType.SOMETHINELSE
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            return ClassType.SOMETHINELSE
        }
    }

    fun Context.showLongToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun Context.showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Time.toProperTime(): String{
        return "$hours : $minutes"
    }

}
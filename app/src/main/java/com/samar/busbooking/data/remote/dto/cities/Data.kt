package com.samar.busbooking.data.remote.dto.cities


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cities")
    val cities: List<City>
)
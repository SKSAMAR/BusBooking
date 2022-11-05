package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("availableTrips")
    val availableTrips: List<AvailableTrip>,
)
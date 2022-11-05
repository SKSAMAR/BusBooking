package com.samar.busbooking.data.remote.dto.tripdetails


import com.google.gson.annotations.SerializedName

data class TripDetailsDto(
    @SerializedName("data")
    var data: TripData?,
    @SerializedName("response_code")
    var responseCode: Int,
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String = "Failed"
)
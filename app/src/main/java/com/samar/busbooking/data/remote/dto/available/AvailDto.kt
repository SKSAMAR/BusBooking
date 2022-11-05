package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class AvailDto(
    @SerializedName("responseString")
    val responseString: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("status")
    val status: Boolean,
    var message: String
)
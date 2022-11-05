package com.samar.busbooking.data.remote.dto.cities


import com.google.gson.annotations.SerializedName

data class CitiesDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("response_code")
    val responseCode: Int,
    @SerializedName("status")
    val status: Boolean,
    var message: String?
)
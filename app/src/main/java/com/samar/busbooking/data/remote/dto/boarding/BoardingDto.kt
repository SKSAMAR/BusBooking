package com.samar.busbooking.data.remote.dto.boarding


import com.google.gson.annotations.SerializedName

data class BoardingDto(
    @SerializedName("data")
    var data: Data?,
    @SerializedName("response_code")
    var responseCode: Int,
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String? = "failed"
)
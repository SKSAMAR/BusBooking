package com.samar.busbooking.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class BookingResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String?,
    @SerializedName("response_code")
    var response_code: Int,
    @SerializedName("data")
    var data: BookedData?

): java.io.Serializable
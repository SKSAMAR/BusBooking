package com.samar.busbooking.data.remote.dto.response.regular

import com.google.gson.annotations.SerializedName

data class RegularResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("response_code")
    var response_code: Int,
    @SerializedName("message")
    var message: String?
)

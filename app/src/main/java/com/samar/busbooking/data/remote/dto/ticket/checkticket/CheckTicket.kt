package com.samar.busbooking.data.remote.dto.ticket.checkticket


import com.google.gson.annotations.SerializedName

data class CheckTicket(
    @SerializedName("data")
    var data: Data?,
    @SerializedName("response_code")
    var responseCode: Int,
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String?
): java.io.Serializable
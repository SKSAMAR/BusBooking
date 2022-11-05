package com.samar.busbooking.data.remote.dto.history


import com.google.gson.annotations.SerializedName
import com.samar.busbooking.data.remote.dto.response.BookingResponse

data class HistoryData(
    @SerializedName("AMOUNT")
    var aMOUNT: String?,
    @SerializedName("DATE")
    var dATE: String?,
    @SerializedName("ID")
    var iD: String?,
    @SerializedName("REF_ID")
    var rEFID: String?,
    @SerializedName("RESPONSE")
    var rESPONSE: BookingResponse?,
    @SerializedName("STATUS")
    var sTATUS: String?,
    @SerializedName("USER_ID")
    var uSERID: String?
)
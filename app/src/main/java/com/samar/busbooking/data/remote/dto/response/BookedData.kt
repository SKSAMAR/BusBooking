package com.samar.busbooking.data.remote.dto.response


import com.google.gson.annotations.SerializedName

data class BookedData(
    @SerializedName("ackno")
    var ackno: Int?,
    @SerializedName("blockKey")
    var blockKey: String?,
    @SerializedName("refid")
    var refid: String?,
    @SerializedName("utr")
    var utr: Int?
): java.io.Serializable
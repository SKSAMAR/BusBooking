package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class Businfo(
    @SerializedName("busNumber")
    val busNumber: String?,
    @SerializedName("driverMobile")
    val driverMobile: String?,
    @SerializedName("driverName")
    val driverName: String?
): java.io.Serializable
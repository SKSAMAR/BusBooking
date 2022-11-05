package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class BoardingTime(
    @SerializedName("address")
    val address: String?,
    @SerializedName("bpId")
    val bpId: String?,
    @SerializedName("bpIdentifier")
    val bpIdentifier: String?,
    @SerializedName("bpName")
    val bpName: String?,
    @SerializedName("contactNumber")
    val contactNumber: String?,
    @SerializedName("landmark")
    val landmark: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("prime")
    val prime: String?,
    @SerializedName("time")
    val time: String?
): java.io.Serializable
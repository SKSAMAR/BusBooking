package com.samar.busbooking.data.remote.dto.boarding


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    var address: String?,
    @SerializedName("contactnumber")
    var contactnumber: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("landmark")
    var landmark: String?,
    @SerializedName("locationName")
    var locationName: String?,
    @SerializedName("name")
    var name: String?
)
package com.samar.busbooking.data.remote.dto.cities


import com.google.gson.annotations.SerializedName
import com.samar.busbooking.domain.model.cities.CityModel

data class City(
    @SerializedName("id")
    val id: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("locationType")
    val locationType: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("stateId")
    val stateId: String?
)

fun City.toCityModel(): CityModel{
    return CityModel(
        id = id?:"",
        latitude = latitude?:"",
        locationType = locationType?:"",
        longitude = longitude?:"",
        name = name?:"",
        state = state?:"",
        stateId = stateId?:""
    )
}
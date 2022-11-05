package com.samar.busbooking.domain.model.cities


data class CityModel(
    val id: String,
    val latitude: String,
    val locationType: String,
    val longitude: String,
    val name: String,
    val state: String,
    val stateId: String
): java.io.Serializable

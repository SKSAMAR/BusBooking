package com.samar.busbooking.data.remote.dto.tripdetails


import com.google.gson.annotations.SerializedName

data class TripData(
    @SerializedName("availableSingleSeat")
    var availableSingleSeat: String?,
    @SerializedName("callFareBreakUpAPI")
    var callFareBreakUpAPI: String?,
    @SerializedName("fareDetails")
    var fareDetails: List<FareDetail>?,
    @SerializedName("noSeatLayoutAvailableSeats")
    var noSeatLayoutAvailableSeats: String?,
    @SerializedName("noSeatLayoutEnabled")
    var noSeatLayoutEnabled: String?,
    @SerializedName("primo")
    var primo: String?,
    @SerializedName("seats")
    var seats: List<Seat> = emptyList(),
    @SerializedName("vaccinatedBus")
    var vaccinatedBus: String?,
    @SerializedName("vaccinatedStaff")
    var vaccinatedStaff: String?
)
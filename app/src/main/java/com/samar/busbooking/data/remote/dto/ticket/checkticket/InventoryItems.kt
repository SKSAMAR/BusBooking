package com.samar.busbooking.data.remote.dto.ticket.checkticket


import com.google.gson.annotations.SerializedName

data class InventoryItems(
    @SerializedName("fare")
    var fare: String?,
    @SerializedName("ladiesSeat")
    var ladiesSeat: String?,
    @SerializedName("malesSeat")
    var malesSeat: String?,
    @SerializedName("operatorServiceCharge")
    var operatorServiceCharge: String?,
    @SerializedName("passenger")
    var passenger: Passenger?,
    @SerializedName("seatName")
    var seatName: String?,
    @SerializedName("serviceTax")
    var serviceTax: String?
): java.io.Serializable{
    override fun toString(): String {
        return "Fare : $fare\n\nLadies Seat : $ladiesSeat\n\nMales Seat : $malesSeat\n\nSeat Name : $seatName".replace("false", "No", true).replace("true", "Yes", true)
    }
}
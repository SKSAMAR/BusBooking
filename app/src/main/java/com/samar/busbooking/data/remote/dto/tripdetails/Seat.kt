package com.samar.busbooking.data.remote.dto.tripdetails


import com.google.gson.annotations.SerializedName
import com.samar.busbooking.domain.model.inventory.InventoryMode
import com.samar.busbooking.domain.model.inventory.PassengerMode
import com.samar.busbooking.domain.model.seat.SeatModel
import com.samar.busbooking.presentation.tripdetails.component.SeatGenderType
import com.samar.busbooking.presentation.tripdetails.component.getPossibleGender

data class Seat(
    @SerializedName("available")
    var available: String?,
    @SerializedName("bankTrexAmt")
    var bankTrexAmt: String?,
    @SerializedName("baseFare")
    var baseFare: String?,
    @SerializedName("childFare")
    var childFare: String?,
    @SerializedName("column")
    var column: String?,
    @SerializedName("concession")
    var concession: String?,
    @SerializedName("doubleBirth")
    var doubleBirth: String?,
    @SerializedName("fare")
    var fare: String?,
    @SerializedName("ladiesSeat")
    var ladiesSeat: String?,
    @SerializedName("length")
    var length: String?,
    @SerializedName("levyFare")
    var levyFare: String?,
    @SerializedName("malesSeat")
    var malesSeat: String?,
    @SerializedName("markupFareAbsolute")
    var markupFareAbsolute: String?,
    @SerializedName("markupFarePercentage")
    var markupFarePercentage: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("operatorServiceChargeAbsolute")
    var operatorServiceChargeAbsolute: String?,
    @SerializedName("operatorServiceChargePercent")
    var operatorServiceChargePercent: String?,
    @SerializedName("reservedForSocialDistancing")
    var reservedForSocialDistancing: String?,
    @SerializedName("row")
    var row: String?,
    @SerializedName("serviceTaxAbsolute")
    var serviceTaxAbsolute: String?,
    @SerializedName("serviceTaxPercentage")
    var serviceTaxPercentage: String?,
    @SerializedName("srtFee")
    var srtFee: String?,
    @SerializedName("tollFee")
    var tollFee: String?,
    @SerializedName("width")
    var width: String?,
    @SerializedName("zIndex")
    var zIndex: String?,
):java.io.Serializable

fun Seat.toSeatModel(): SeatModel {
    return SeatModel(available, bankTrexAmt, baseFare, childFare, column, concession, doubleBirth, fare, ladiesSeat, length, levyFare, malesSeat, markupFareAbsolute, markupFarePercentage, name, operatorServiceChargeAbsolute, operatorServiceChargePercent, reservedForSocialDistancing, row, serviceTaxAbsolute, serviceTaxPercentage, srtFee, tollFee, width, zIndex)
}

fun Seat.getSeatType(): SeatGenderType?{

    if (malesSeat == "false" && ladiesSeat == "false" && available == "true" && length == "1"){
        return SeatGenderType.AvailableSeat
    }

    if (malesSeat == "false" && ladiesSeat == "false" && available == "false" && length == "1"){
        return SeatGenderType.BlockedSeat
    }

    if (malesSeat == "true" && available == "true" && length == "1"){
        return SeatGenderType.AvailableMaleSeat
    }

    if (malesSeat == "true" && available == "false" && length == "1"){
        return SeatGenderType.BlockedMaleSeat
    }

    if (ladiesSeat == "true" && available == "true" && length == "1"){
        return SeatGenderType.AvailableFemaleSeat
    }

    if (ladiesSeat == "true" && available == "false" && length == "1"){
        return SeatGenderType.BlockedFemaleSeat
    }


    if (malesSeat == "false" && ladiesSeat == "false" && available == "true" && length == "2"){
        return SeatGenderType.AvailableSleeper
    }

    if (malesSeat == "false" && ladiesSeat == "false" && available == "false" && length == "2"){
        return SeatGenderType.BlockedSleeper
    }

    if (malesSeat == "true" && available == "true" && length == "2"){
        return SeatGenderType.AvailableMaleSleeper
    }

    if (malesSeat == "true" && available == "false" && length == "2"){
        return SeatGenderType.BlockedMaleSleeper
    }

    if (ladiesSeat == "true" && available == "true" && length == "2"){
        return SeatGenderType.AvailableFemaleSleeper
    }

    if (ladiesSeat == "true" && available == "false" && length == "2"){
        return SeatGenderType.BlockedFemaleSleeper
    }

    return null
}

fun Seat.toInventoryMode(): InventoryMode{
    return InventoryMode(
        genderSelection = this.getSeatType(),
        seatName = name?:"null",
        fare = fare?:"null",
        serviceTax = serviceTaxAbsolute?:"",
        operatorServiceCharge = operatorServiceChargeAbsolute?:"",
        ladiesSeat = ladiesSeat?:"",
        passengerMode = PassengerMode()
    )
}
package com.samar.busbooking.domain.model.inventory

import com.samar.busbooking.presentation.tripdetails.component.SeatGenderType

data class InventoryItem(
    val seatName: String,
    val fare: String,
    val serviceTax: String,
    val operatorServiceCharge: String,
    val ladiesSeat: String,
    val passenger: Passenger
){
    override fun toString(): String {
        return "InventoryItem(seatName='$seatName', fare='$fare', serviceTax='$serviceTax', operatorServiceCharge='$operatorServiceCharge', ladiesSeat='$ladiesSeat', passenger=$passenger)"
    }
}



fun InventoryItem.toInventoryMode(): InventoryMode {
    return InventoryMode(
        seatName = seatName, fare = fare,
        serviceTax = serviceTax, operatorServiceCharge = operatorServiceCharge,
        ladiesSeat = ladiesSeat, passengerMode = passenger.toPassengerMode()
    )
}

data class InventoryMode(
    val genderSelection: SeatGenderType? = null,
    val seatName: String,
    val fare: String,
    val serviceTax: String,
    val operatorServiceCharge: String,
    val ladiesSeat: String,
    val passengerMode: PassengerMode
)

fun InventoryMode.toInventoryItem(): InventoryItem {
    return InventoryItem(
        seatName = seatName,
        fare = fare,
        serviceTax = serviceTax,
        operatorServiceCharge = operatorServiceCharge,
        ladiesSeat = ladiesSeat,
        passenger = passengerMode.toPassenger()
    )
}
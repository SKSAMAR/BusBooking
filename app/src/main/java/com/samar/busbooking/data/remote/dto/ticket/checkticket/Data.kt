package com.samar.busbooking.data.remote.dto.ticket.checkticket


import com.google.gson.annotations.SerializedName
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.common.ViewUtils.toProperTime

data class Data(
    @SerializedName("bookingFee")
    var bookingFee: String?,
    @SerializedName("busType")
    var busType: String?,
    @SerializedName("cancellationCalculationTimestamp")
    var cancellationCalculationTimestamp: String?,
    @SerializedName("cancellationMessage")
    var cancellationMessage: String?,
    @SerializedName("cancellationPolicy")
    var cancellationPolicy: String?,
    @SerializedName("dateOfIssue")
    var dateOfIssue: String?,
    @SerializedName("destinationCity")
    var destinationCity: String?,
    @SerializedName("destinationCityId")
    var destinationCityId: String?,
    @SerializedName("doj")
    var doj: String?,
    @SerializedName("dropLocation")
    var dropLocation: String?,
    @SerializedName("dropLocationAddress")
    var dropLocationAddress: String?,
    @SerializedName("dropLocationId")
    var dropLocationId: String?,
    @SerializedName("dropLocationLandmark")
    var dropLocationLandmark: String?,
    @SerializedName("dropTime")
    var dropTime: String?,
    @SerializedName("firstBoardingPointTime")
    var firstBoardingPointTime: String?,
    @SerializedName("hasRTCBreakup")
    var hasRTCBreakup: String?,
    @SerializedName("hasSpecialTemplate")
    var hasSpecialTemplate: String?,
    @SerializedName("inventoryId")
    var inventoryId: String?,
    @SerializedName("inventoryItems")
    var inventoryItems: List<InventoryItems>?,
    @SerializedName("MTicketEnabled")
    var mTicketEnabled: String?,
    @SerializedName("partialCancellationAllowed")
    var partialCancellationAllowed: String?,
    @SerializedName("pickUpContactNo")
    var pickUpContactNo: String?,
    @SerializedName("pickUpLocationAddress")
    var pickUpLocationAddress: String?,
    @SerializedName("pickupLocation")
    var pickupLocation: String?,
    @SerializedName("pickupLocationId")
    var pickupLocationId: String?,
    @SerializedName("pickupLocationLandmark")
    var pickupLocationLandmark: String?,
    @SerializedName("pickupTime")
    var pickupTime: String?,
    @SerializedName("pnr")
    var pnr: String?,
    @SerializedName("primeDepartureTime")
    var primeDepartureTime: String?,
    @SerializedName("primoBooking")
    var primoBooking: String?,
    @SerializedName("serviceCharge")
    var serviceCharge: String?,
    @SerializedName("sourceCity")
    var sourceCity: String?,
    @SerializedName("sourceCityId")
    var sourceCityId: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("tin")
    var tin: String?,
    @SerializedName("travels")
    var travels: String?,
    @SerializedName("vaccinatedBus")
    var vaccinatedBus: String?,
    @SerializedName("vaccinatedStaff")
    var vaccinatedStaff: String?
): java.io.Serializable{

    override fun toString(): String {
        return "Bus Type : $busType\n\nCancellation Message : $cancellationMessage\n\nCancellation Policy : $cancellationPolicy\n\nDate Of Issue : $dateOfIssue\n\nDestination City : $destinationCity\n\nDOJ : $doj\n\nDrop Location : $dropLocation\n\nDrop Location Address : $dropLocationAddress\n\nDrop Location Id : $dropLocationId\n\ndrop Location Landmark : $dropLocationLandmark\n\nDrop Time : ${dropTime?.timeFromMinutesToActual()?.toProperTime()}\n\nFirst Boarding Point Time : ${firstBoardingPointTime?.timeFromMinutesToActual()?.toProperTime()}\n\nHas RTC Breakup : $hasRTCBreakup\n\nHas Special Template : $hasSpecialTemplate\n\nInventory Id : $inventoryId\n\nmTicket Enabled : $mTicketEnabled\n\nPartial Cancellation Allowed : $partialCancellationAllowed\n\nPickUp Contact No : $pickUpContactNo\n\nPickUp Location Address : $pickUpLocationAddress\n\nPickupLocation : $pickupLocation\n\nPickup Location Landmark : $pickupLocationLandmark\n\nPickUp Time : ${pickupTime?.timeFromMinutesToActual()?.toProperTime()}\n\nPNR : $pnr\n\nPrime Departure Time : ${primeDepartureTime?.timeFromMinutesToActual()?.toProperTime()}\n\nPrimo Booking : $primoBooking\n\nService Charge : $serviceCharge\n\nSource City : $sourceCity\n\nSource City Id : $sourceCityId\n\nStatus : $status\n\nTIN : $tin\n\nTravels : $travels\n\nVaccinated Bus : $vaccinatedBus\n\nVaccinated Staff : $vaccinatedStaff".replace("false","No", true).replace("true","Yes", true)
    }
}
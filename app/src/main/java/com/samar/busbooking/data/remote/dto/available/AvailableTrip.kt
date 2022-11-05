package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class AvailableTrip(

    @SerializedName("AC")
    val aC: String?,
    @SerializedName("additionalCommission")
    val additionalCommission: String?,
    @SerializedName("agentServiceCharge")
    val agentServiceCharge: String?,
    @SerializedName("agentServiceChargeAllowed")
    val agentServiceChargeAllowed: String?,
    @SerializedName("allowLadiesToBookDoubletitles")
    val allowLadiesToBookDoubleSeats: String?,
    @SerializedName("allowLadyNextToMale")
    val allowLadyNextToMale: String?,
    @SerializedName("arrivalTime")
    val arrivalTime: String?,
    @SerializedName("availCatCard")
    val availCatCard: String?,
    @SerializedName("availSrCitizen")
    val availSrCitizen: String?,
    @SerializedName("availableSeats")
    val availableSeats: String?,
    @SerializedName("availableSingleSeat")
    val availableSingleSeat: String?,
    @SerializedName("avlWindowSeats")
    val avlWindowSeats: String?,
    @SerializedName("boCommission")
    val boCommission: String?,
    @SerializedName("boPriorityOperator")
    val boPriorityOperator: String?,
    @SerializedName("boardingTimes")
    val boardingTimes: List<BoardingTime?>?,
    @SerializedName("bookable")
    val bookable: String?,
    @SerializedName("bpDpSeatLayout")
    val bpDpSeatLayout: String?,
    @SerializedName("busCancelled")
    val busCancelled: String?,
    @SerializedName("busImageCount")
    val busImageCount: String?,
    @SerializedName("busRoutes")
    val busRoutes: String?,
    @SerializedName("busServiceId")
    val busServiceId: String?,
    @SerializedName("busType")
    val busType: String?,
    @SerializedName("busTypeId")
    val busTypeId: String?,
    @SerializedName("businfo")
    val businfo: Businfo?,
    @SerializedName("callFareBreakUpAPI")
    val callFareBreakUpAPI: String?,
    @SerializedName("cancellationCalculationTimestamp")
    val cancellationCalculationTimestamp: String?,
    @SerializedName("cancellationPolicy")
    val cancellationPolicy: String?,
    @SerializedName("cpId")
    val cpId: String?,
    @SerializedName("departureTime")
    val departureTime: String?,
    @SerializedName("destination")
    val destination: String?,
    @SerializedName("doj")
    val doj: String?,
    @SerializedName("dropPointMandatory")
    val dropPointMandatory: String?,
    @SerializedName("droppingTimes")
    val droppingTimes: List<DroppingTime?>?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("exactSearch")
    val exactSearch: String?,
    @SerializedName("fareDetails")
    val fareDetails: List<FareDetail?>?,
    @SerializedName("fares")
    val fares: List<String?>?,
    @SerializedName("flatComApplicable")
    val flatComApplicable: String?,
    @SerializedName("flatSSComApplicable")
    val flatSSComApplicable: String?,
    @SerializedName("forcedSeats")
    val forcedSeats: String?,
    @SerializedName("gdsCommission")
    val gdsCommission: String?,
    @SerializedName("groupOfferPriceEnabled")
    val groupOfferPriceEnabled: String?,
    @SerializedName("happyHours")
    val happyHours: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("idProofRequired")
    val idProofRequired: String?,
    @SerializedName("imagesMetadataUrl")
    val imagesMetadataUrl: String?,
    @SerializedName("isLMBAllowed")
    val isLMBAllowed: String?,
    @SerializedName("liveTrackingAvailable")
    val liveTrackingAvailable: String?,
    @SerializedName("mTicketEnabled")
    val mTicketEnabled: String?,
    @SerializedName("maxSeatsPerTicket")
    val maxSeatsPerTicket: String?,
    @SerializedName("nextDay")
    val nextDay: String?,
    @SerializedName("noSeatLayoutAvailableSeats")
    val noSeatLayoutAvailableSeats: String?,
    @SerializedName("noSeatLayoutEnabled")
    val noSeatLayoutEnabled: String?,
    @SerializedName("nonAC")
    val nonAC: String?,
    @SerializedName("offerPriceEnabled")
    val offerPriceEnabled: String?,
    @SerializedName("operator")
    val `operator`: String?,
    @SerializedName("otgEnabled")
    val otgEnabled: String?,
    @SerializedName("otgPolicy")
    val otgPolicy: String?,
    @SerializedName("partialCancellationAllowed")
    val partialCancellationAllowed: String?,
    @SerializedName("partnerBaseCommission")
    val partnerBaseCommission: String?,
    @SerializedName("primaryPaxCancellable")
    val primaryPaxCancellable: String?,
    @SerializedName("primo")
    val primo: String?,
    @SerializedName("routeId")
    val routeId: String?,
    @SerializedName("rtc")
    val rtc: String?,
    @SerializedName("SSAgentAccount")
    val sSAgentAccount: String?,
    @SerializedName("seater")
    val seater: String?,
    @SerializedName("selfInventory")
    val selfInventory: String?,
    @SerializedName("serviceStartTime")
    val serviceStartTime: String?,
    @SerializedName("singleLadies")
    val singleLadies: String?,
    @SerializedName("sleeper")
    val sleeper: String?,
    @SerializedName("socialDistancing")
    val socialDistancing: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("spotTime")
    val spotTime: String?,
    @SerializedName("tatkalTime")
    val tatkalTime: String?,
    @SerializedName("travels")
    val travels: String?,
    @SerializedName("unAvailable")
    val unAvailable: String?,
    @SerializedName("vaccinatedBus")
    val vaccinatedBus: String?,
    @SerializedName("vaccinatedStaff")
    val vaccinatedStaff: String?,
    @SerializedName("vehicleType")
    val vehicleType: String?,
    @SerializedName("viaRoutes")
    val viaRoutes: String?,
    @SerializedName("zeroCancellationTime")
    val zeroCancellationTime: String?
): java.io.Serializable



fun AvailableTrip.getBaseFares():String?{


//    try {
//        var amount :Float? = this.fares?.first()?.toFloat()
//        this.fares?.forEach {
//            try {
//                if (it!!.toFloat() < amount!!.toFloat()){
//                    amount = it.toFloat()
//                }
//            }catch (e: NullPointerException){
//                e.printStackTrace()
//            }catch (e: ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: NumberFormatException){
//                e.printStackTrace()
//            }
//        }
//        return DecimalFormat("#.##")
//            .apply { roundingMode = RoundingMode.FLOOR }
//            .format(amount)
//    }catch (e: NullPointerException){
//        e.printStackTrace()
//    }catch (e: ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: NumberFormatException){
//        e.printStackTrace()
//    }
    return null
}

fun AvailableTrip.getFirstBoardingTime():String?{

//    try {
//        var time :Int? = this.boardingTimes?.first()?.time?.toInt()
//        this.fares?.forEach {
//            try {
//                if (it!!.toInt() < time!!.toInt()){
//                    time = it.toInt()
//                }
//            }catch (e: NullPointerException){
//                e.printStackTrace()
//            }catch (e: ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: NumberFormatException){
//                e.printStackTrace()
//            }
//        }
//        return time.toString()
//    }catch (e: NullPointerException){
//        e.printStackTrace()
//    }catch (e: ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: NumberFormatException){
//        e.printStackTrace()
//    }
    return null
}

fun AvailableTrip.getFirstDepartureTime():String?{

//    try {
//        var time :Int? = this.droppingTimes?.last()?.time?.toInt()
//        this.fares?.forEach {
//            try {
//                if (it!!.toInt() < time!!.toInt()){
//                    time = it.toInt()
//                }
//            }catch (e: NullPointerException){
//                e.printStackTrace()
//            }catch (e: ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//                e.printStackTrace()
//            }catch (e: NumberFormatException){
//                e.printStackTrace()
//            }
//        }
//        return time.toString()
//    }catch (e: NullPointerException){
//        e.printStackTrace()
//    }catch (e: ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: java.lang.ArrayIndexOutOfBoundsException){
//        e.printStackTrace()
//    }catch (e: NumberFormatException){
//        e.printStackTrace()
//    }
    return null
}
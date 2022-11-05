package com.samar.busbooking.data.remote.dto.tripdetails


import com.google.gson.annotations.SerializedName

data class FareDetail(
    @SerializedName("bankTrexAmt")
    var bankTrexAmt: String?,
    @SerializedName("baseFare")
    var baseFare: String?,
    @SerializedName("bookingFee")
    var bookingFee: String?,
    @SerializedName("childFare")
    var childFare: String?,
    @SerializedName("gst")
    var gst: String?,
    @SerializedName("levyFare")
    var levyFare: String?,
    @SerializedName("markupFareAbsolute")
    var markupFareAbsolute: String?,
    @SerializedName("markupFarePercentage")
    var markupFarePercentage: String?,
    @SerializedName("opFare")
    var opFare: String?,
    @SerializedName("opGroupFare")
    var opGroupFare: String?,
    @SerializedName("operatorServiceChargeAbsolute")
    var operatorServiceChargeAbsolute: String?,
    @SerializedName("operatorServiceChargePercentage")
    var operatorServiceChargePercentage: String?,
    @SerializedName("serviceCharge")
    var serviceCharge: String?,
    @SerializedName("serviceTaxAbsolute")
    var serviceTaxAbsolute: String?,
    @SerializedName("serviceTaxPercentage")
    var serviceTaxPercentage: String?,
    @SerializedName("srtFee")
    var srtFee: String?,
    @SerializedName("tollFee")
    var tollFee: String?,
    @SerializedName("totalFare")
    var totalFare: String?
)
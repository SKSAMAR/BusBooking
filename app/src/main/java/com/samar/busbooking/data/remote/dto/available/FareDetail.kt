package com.samar.busbooking.data.remote.dto.available


import com.google.gson.annotations.SerializedName

data class FareDetail(
    @SerializedName("bankTrexAmt")
    val bankTrexAmt: String?,
    @SerializedName("baseFare")
    val baseFare: String?,
    @SerializedName("bookingFee")
    val bookingFee: String?,
    @SerializedName("childFare")
    val childFare: String?,
    @SerializedName("gst")
    val gst: String?,
    @SerializedName("levyFare")
    val levyFare: String?,
    @SerializedName("markupFareAbsolute")
    val markupFareAbsolute: String?,
    @SerializedName("markupFarePercentage")
    val markupFarePercentage: String?,
    @SerializedName("opFare")
    val opFare: String?,
    @SerializedName("opGroupFare")
    val opGroupFare: String?,
    @SerializedName("operatorServiceChargeAbsolute")
    val operatorServiceChargeAbsolute: String?,
    @SerializedName("operatorServiceChargePercentage")
    val operatorServiceChargePercentage: String?,
    @SerializedName("serviceCharge")
    val serviceCharge: String?,
    @SerializedName("serviceTaxAbsolute")
    val serviceTaxAbsolute: String?,
    @SerializedName("serviceTaxPercentage")
    val serviceTaxPercentage: String?,
    @SerializedName("srtFee")
    val srtFee: String?,
    @SerializedName("tollFee")
    val tollFee: String?,
    @SerializedName("totalFare")
    val totalFare: String?
): java.io.Serializable {
    override fun toString(): String {
        return "FareDetail(bankTrexAmt=$bankTrexAmt, baseFare=$baseFare, bookingFee=$bookingFee, childFare=$childFare, gst=$gst, levyFare=$levyFare, markupFareAbsolute=$markupFareAbsolute, markupFarePercentage=$markupFarePercentage, opFare=$opFare, opGroupFare=$opGroupFare, operatorServiceChargeAbsolute=$operatorServiceChargeAbsolute, operatorServiceChargePercentage=$operatorServiceChargePercentage, serviceCharge=$serviceCharge, serviceTaxAbsolute=$serviceTaxAbsolute, serviceTaxPercentage=$serviceTaxPercentage, srtFee=$srtFee, tollFee=$tollFee, totalFare=$totalFare)"
    }
}
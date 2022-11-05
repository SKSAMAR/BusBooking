package com.samar.busbooking.domain.model.seat

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SeatModel(
    var available: String?,
    var bankTrexAmt: String?,
    var baseFare: String?,
    var childFare: String?,
    var column: String?,
    var concession: String?,
    var doubleBirth: String?,
    var fare: String?,
    var ladiesSeat: String?,
    var length: String?,
    var levyFare: String?,
    var malesSeat: String?,
    var markupFareAbsolute: String?,
    var markupFarePercentage: String?,
    var name: String?,
    var operatorServiceChargeAbsolute: String?,
    var operatorServiceChargePercent: String?,
    var reservedForSocialDistancing: String?,
    var row: String?,
    var serviceTaxAbsolute: String?,
    var serviceTaxPercentage: String?,
    var srtFee: String?,
    var tollFee: String?,
    var width: String?,
    var zIndex: String?,
    var isSeatSelected: MutableState<Boolean> = mutableStateOf(false)
)

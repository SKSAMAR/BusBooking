package com.samar.busbooking.domain.model

import java.util.Date

data class DateModel(
    val classicDate: String,
    val englishData: String,
    val time: String? = null,
    val day: String,
    val date: Date
): java.io.Serializable

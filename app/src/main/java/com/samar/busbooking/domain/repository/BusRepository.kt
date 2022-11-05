package com.samar.busbooking.domain.repository

import com.samar.busbooking.data.remote.dto.available.AvailDto
import com.samar.busbooking.data.remote.dto.boarding.BoardingDto
import com.samar.busbooking.data.remote.dto.cities.CitiesDto
import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.data.remote.dto.response.regular.RegularResponse
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.data.remote.dto.tripdetails.TripDetailsDto
import retrofit2.http.Field
import java.util.*

interface BusRepository {

    suspend fun getCities(): CitiesDto

    suspend fun getAvail(
        fromdest: String,
        todest: String,
        date: String
    ): AvailDto

    suspend fun getTripDetails(
        tripid: String,
    ): TripDetailsDto

    suspend fun checkBoardingPoint(
        tripid: String,
        bpid: String,
    ): BoardingDto

    suspend fun blockYourTickets(
        aviltripid: String,
        boardingpid: String,
        dpid: String,
        destid: String,
        arvialid: String,
        jsonpendata: String,
        totalFare: String,
        basefare: String
    ): BookingResponse

    suspend fun getYourHistories(): List<HistoryData>

    suspend fun checkTicket(
         referenceId: String,
    ): CheckTicket

    suspend fun cancelTicket(
        @Field("referenceID") referenceID: String,
        @Field("seatsToCancel") seatsToCancel: String,
    ): BookingResponse
}
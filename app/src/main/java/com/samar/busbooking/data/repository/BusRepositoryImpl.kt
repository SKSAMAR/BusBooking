package com.samar.busbooking.data.repository

import com.samar.busbooking.data.remote.BusApi
import com.samar.busbooking.data.remote.dto.available.AvailDto
import com.samar.busbooking.data.remote.dto.boarding.BoardingDto
import com.samar.busbooking.data.remote.dto.cities.CitiesDto
import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.data.remote.dto.response.regular.RegularResponse
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.data.remote.dto.tripdetails.TripDetailsDto
import com.samar.busbooking.domain.repository.BusRepository
import java.util.*
import javax.inject.Inject

class BusRepositoryImpl
@Inject constructor(private val busApi: BusApi): BusRepository {

    override suspend fun getCities(): CitiesDto {
        return busApi.getCities()
    }

    override suspend fun getAvail(fromdest: String, todest: String, date: String): AvailDto {
        return busApi.getAvail(fromdest, todest, date)
    }

    override suspend fun getTripDetails(tripid: String): TripDetailsDto {
        return busApi.getTripDetails(tripid = tripid)
    }

    override suspend fun checkBoardingPoint(tripid: String, bpid: String): BoardingDto {
        return busApi.checkBoardingPoint(tripid, bpid)
    }

    override suspend fun blockYourTickets(
        aviltripid: String,
        boardingpid: String,
        dpid: String,
        destid: String,
        arvialid: String,
        jsonpendata: String,
        totalFare: String,
        basefare: String
    ): BookingResponse{
        return busApi.blockYourTickets(aviltripid, boardingpid, dpid, destid, arvialid, jsonpendata, totalFare, basefare)
    }

    override suspend fun getYourHistories(): List<HistoryData>{
        return busApi.getYourHistories()
    }

    override suspend fun checkTicket(referenceId: String): CheckTicket {
        return busApi.checkTicket(referenceId)
    }

    override suspend fun cancelTicket(referenceID: String, seatsToCancel: String): BookingResponse {
        return busApi.cancelTicket(referenceID, seatsToCancel)
    }

}
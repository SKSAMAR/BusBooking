package com.samar.busbooking.data.remote

import com.samar.busbooking.data.remote.dto.available.AvailDto
import com.samar.busbooking.data.remote.dto.boarding.BoardingDto
import com.samar.busbooking.data.remote.dto.cities.CitiesDto
import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.data.remote.dto.response.regular.RegularResponse
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.data.remote.dto.tripdetails.TripDetailsDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.Objects

interface BusApi {

    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun getCities(@Field("cities") cities: String = "cities"): CitiesDto


    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    @Headers("Accept: application/json")
    suspend fun getAvail(
        @Field("fromdest") fromdest: String,
        @Field("todest") todest: String,
        @Field("date") date: String
    ): AvailDto

    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun getTripDetails(
        @Field("tripid") tripid: String,
        @Field("gettripdetails") gettripdetails: String = "gettripdetails"
    ): TripDetailsDto


    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun checkBoardingPoint(
        @Field("tripid") tripid: String,
        @Field("bpid") bpid: String,
    ): BoardingDto

    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun blockYourTickets(
        @Field("aviltripid") aviltripid: String,
        @Field("boardingpid") boardingpid: String,
        @Field("dpid") dpid: String,
        @Field("destid") destid: String,
        @Field("arvialid") arvialid: String,
        @Field("jsonpendata") jsonpendata: String,
        @Field("totalFare") totalFare: String,
        @Field("basefare") basefare: String
    ): BookingResponse


    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun getYourHistories(@Field("getBookedHistory") getBookedHistory: String = "getBookedHistory"): List<HistoryData>

    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun checkTicket(
        @Field("referenceId") referenceId: String,
        @Field("checkTicketHistory") checkTicketHistory: String = "checkTicketHistory"
    ): CheckTicket

    @FormUrlEncoded
    @POST("Agent/Backend/ticket_booking/main.php")
    suspend fun cancelTicket(
        @Field("referenceID") referenceID: String,
        @Field("seatsToCancel") seatsToCancel: String,
        @Field("cancelTicket") cancelTicket: String = "cancelTicket"
    ): BookingResponse


}
package com.samar.busbooking.domain.usecases.blockusecase

import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBlockUseCase
@Inject constructor(private val repository: BusRepository) {

    fun blockYourTicket(
        aviltripid: String,
        boardingpid: String,
        dpid: String,
        destid: String,
        arvialid: String,
        jsonpendata: String,
        totalFare: String,
        basefare: String

    ): Flow<Resource<BookingResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.blockYourTickets(aviltripid, boardingpid, dpid, destid, arvialid, jsonpendata, totalFare, basefare)
            if (response.status && response.response_code == 1) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message?:"Some Failure"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        } catch (e: Throwable) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }



}
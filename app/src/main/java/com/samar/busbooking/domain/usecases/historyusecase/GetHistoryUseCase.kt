package com.samar.busbooking.domain.usecases.historyusecase

import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.data.remote.dto.response.BookingResponse
import com.samar.busbooking.data.remote.dto.response.regular.RegularResponse
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.http.Field
import java.io.IOException
import javax.inject.Inject

class GetHistoryUseCase
@Inject constructor(private val repository: BusRepository) {

    operator fun invoke(): Flow<Resource<List<HistoryData>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getYourHistories()
            if (response.isNotEmpty()) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error("No Success Booking Record Found"))
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


    fun checkTicket(
        referenceId: String,
    ): Flow<Resource<CheckTicket>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.checkTicket(referenceId = referenceId)
            if (response.status && response.responseCode == 1) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message?:"Failed, Try Again later some time"))
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


    fun cancelTicket(
        referenceID: String,
        seatsToCancel: String,
    ): Flow<Resource<BookingResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.cancelTicket(referenceID, seatsToCancel)
            if (response.status && response.response_code == 1) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.message?:"Failed, Try Again later some time"))
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
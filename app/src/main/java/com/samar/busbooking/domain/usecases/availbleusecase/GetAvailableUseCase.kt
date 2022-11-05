package com.samar.busbooking.domain.usecases.availbleusecase

import android.util.Log
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.available.Data
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAvailableUseCase
@Inject constructor(private val repository: BusRepository) {

    fun getAvails(
        fromdest: String,
        todest: String,
        date: String
    ): Flow<Resource<List<AvailableTrip>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getAvail(fromdest, todest, date)
            if (response.status && response.responseCode == 1) {
                emit(Resource.Success(response.data.availableTrips))
            } else {
                emit(Resource.Error(response.message))
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
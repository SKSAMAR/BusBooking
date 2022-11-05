package com.samar.busbooking.domain.usecases.tripdetails

import com.samar.busbooking.data.remote.dto.tripdetails.TripData
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTripDetailUseCase
@Inject constructor(private val repository: BusRepository) {

    fun getTripDetails(
        tripid: String,
    ): Flow<Resource<TripData>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getTripDetails(tripid = tripid)
            if (response.status && response.responseCode == 1 && response.data !=null ) {
                emit(Resource.Success(response.data!!))
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
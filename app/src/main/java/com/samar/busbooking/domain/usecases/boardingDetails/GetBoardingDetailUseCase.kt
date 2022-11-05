package com.samar.busbooking.domain.usecases.boardingDetails

import com.samar.busbooking.data.remote.dto.boarding.BoardingDto
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class GetBoardingDetailUseCase
@Inject constructor(private val repository: BusRepository) {

    fun getBoardingDetails(
        tripid: String,
        bpid: String
    ): Flow<Resource<BoardingDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.checkBoardingPoint(tripid = tripid, bpid = bpid)
            if (response.status && response.responseCode == 1 && response.data !=null ) {
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
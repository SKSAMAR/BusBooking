package com.samar.busbooking.domain.usecases.citiesusecase

import com.samar.busbooking.data.remote.dto.cities.toCityModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.repository.BusRepository
import com.samar.busbooking.util.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCitiesUseCase
@Inject constructor(private val repository: BusRepository) {

    operator fun invoke(): Flow<Resource<List<CityModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getCities()
            if(response.status && response.responseCode == 1){
                val list = response.data.cities.map { it.toCityModel() }
                emit(Resource.Success(list))
            }
            else{
                emit(Resource.Error(response.message?:"Some Error From Upside"))
            }
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error(e.localizedMessage?:"Couldn't reach server. Check your internet connection."))
        } catch(e: Throwable) {
            emit(Resource.Error(e.localizedMessage?:"Couldn't reach server. Check your internet connection."))
        } catch(e: Exception) {
            emit(Resource.Error(e.localizedMessage?:"Couldn't reach server. Check your internet connection."))
        }
    }
}
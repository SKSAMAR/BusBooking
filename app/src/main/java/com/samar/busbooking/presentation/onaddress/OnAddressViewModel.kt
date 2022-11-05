package com.samar.busbooking.presentation.onaddress

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.usecases.citiesusecase.GetCitiesUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.util.common.Resource
import com.samar.busbooking.util.preferences.PathUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class OnAddressViewModel
@Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : BaseViewModel<List<CityModel>>() {
    val searchLocation = mutableStateOf("")
    var pathUtil: PathUtil? = null

    companion object{
        var cities = emptyList<CityModel>()
    }

    init {
        if (cities.isNotEmpty()){
            _state.value = ScreenState(receivedResponse = cities)
        }else{
            getCities()
        }
    }

    private fun getCities() {
        getCitiesUseCase.invoke().onEach {
            when (it) {
                is Resource.Success -> {
                    cities = it.data?: emptyList()
                    _state.value = ScreenState(receivedResponse = it.data)
                }
                is Resource.Error -> {
                    _state.value = ScreenState(error = it.message ?: "Some Error")
                }
                is Resource.Loading -> {
                    _state.value = ScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    @Composable
    fun getFilteredList(): List<CityModel> {
        if (searchLocation.value.trim().isNotEmpty()){
            _state.value.receivedResponse?.let {list->
                return  list.filter { s -> s.name.lowercase().contains(searchLocation.value.lowercase(), true)}
            }
            return emptyList()
        }
        return _state.value.receivedResponse?: emptyList()
    }


}
package com.samar.busbooking.presentation.available

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.usecases.availbleusecase.GetAvailableUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.util.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AvailableViewModel
@Inject constructor(private val availableUseCase: GetAvailableUseCase) :
    BaseViewModel<List<AvailableTrip>>() {

    var fromCityModel: CityModel? = null
    var toCityModel: CityModel? = null
    var date: DateModel? = null

    var search =  mutableStateOf("")


    fun getAvails() {
        val fromDest = fromCityModel?.id ?: ""
        val toDest = toCityModel?.id ?: ""
        val date = this.date?.classicDate ?: ""
        availableUseCase.getAvails(fromDest, toDest, date).onEach {
            when (it) {
                is Resource.Success -> {
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
    fun getTotalBusesNumber(): Int? = _state.value.receivedResponse?.size

    @Composable
    fun getTotalAvailableSeats(): Int {
        var i = 0
        _state.value.receivedResponse?.forEach {
            try {
                i += it.availableSingleSeat?.toInt() ?: 0
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
        return i
    }

    @Composable
    fun boardingLocations(): List<String> {
        val boardingPlaces = HashSet<String>()
        _state.value.receivedResponse?.forEach { trips ->
            trips.boardingTimes?.forEach { time ->
                time?.let { timeData ->
                    timeData.location?.let {
                        if (it.length <= 10) {
                            boardingPlaces.add(it.trim())
                        }
                    }
                }
            }
        }

        return bubbleSort(boardingPlaces.toList())
    }

    private fun bubbleSort(xlist: List<String>): List<String> {
        val aList = ArrayList<String>(xlist)
        var swap = true
        while (swap) {
            swap = false
            for (i in 0 until aList.size - 1) {
                if (aList[i].length > aList[i + 1].length) {
                    val temp = aList[i]
                    aList[i] = aList[i + 1]
                    aList[i + 1] = temp
                    swap = true
                }
            }
        }
        return aList
    }


}
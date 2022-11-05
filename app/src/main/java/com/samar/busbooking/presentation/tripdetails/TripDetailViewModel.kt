package com.samar.busbooking.presentation.tripdetails

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.data.remote.dto.tripdetails.TripData
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.usecases.tripdetails.GetTripDetailUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Resource
import com.samar.busbooking.util.common.ViewUtils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel
@Inject constructor(private val getTripDetailUseCase: GetTripDetailUseCase) :
    BaseViewModel<List<Seat>>() {

    var availableTrip: AvailableTrip? = null
    val selectedPrice = mutableStateOf(Constant.AllPrices)
    var selectedSeats = HashMap<String, Seat>()
    var finalSeatsSelected by mutableStateOf(emptyList<Seat>())
    var fromCityModel: CityModel? = null
    var toCityModel: CityModel? = null
    var date: DateModel? = null
    var tripData: TripData? = null
    var tripId = "";


    fun getTrips() {
        getTripDetailUseCase.getTripDetails(tripid = tripId).onEach {
            when (it) {
                is Resource.Success -> {
                    tripData = it.data
                    _state.value = ScreenState(receivedResponse = it.data?.seats)
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


    fun getFaresList(): List<String> {
        val set = HashSet<String>()
        tripData?.fareDetails?.forEach { fare ->
            val baseFare = fare.baseFare ?: "0"
            try {
                if (baseFare != "0") {
                    set.add(baseFare)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return set.toList()
    }

    fun addSeats(context: Context, seat: Seat, action: (Boolean) -> Unit) {
        if (selectedSeats.containsKey(seat.name)) {
            selectedSeats.remove(seat.name)
            action(false)
        } else {
            try {
                val max = availableTrip?.maxSeatsPerTicket?.toInt() ?: 0
                if (selectedSeats.size < max) {
                    selectedSeats[seat.name?:""] = seat
                    action(true)
                }
                else{
                    context.showToast("Maximum seats per ticket is $max")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        finalSeatsSelected = selectedSeats.map { s->s.value }
    }

    fun getTotalBaseFare(): Float{
        var amount = 0.0f
        finalSeatsSelected.forEach {
            it.baseFare?.let {base->
                amount += base.toFloat()
            }
        }
        return amount
    }
}
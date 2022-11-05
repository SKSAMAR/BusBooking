package com.samar.busbooking.presentation.bodrop

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.samar.busbooking.data.remote.BusApi
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.available.BoardingTime
import com.samar.busbooking.data.remote.dto.available.DroppingTime
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.usecases.boardingDetails.GetBoardingDetailUseCase
import com.samar.busbooking.presentation.blockticket.BlockTicketActivity
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Resource
import com.samar.busbooking.util.common.ViewUtils.showLongToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BoDropViewModel
@Inject constructor(private val getBoardingDetailUseCase: GetBoardingDetailUseCase) :
    BaseViewModel<AvailableTrip>() {

    var pageType by mutableStateOf(PageType.BOARDING)
    var selectedSeats = ArrayList<Seat>()
    var fromCityModel: CityModel? = null
    var toCityModel: CityModel? = null
    var date: DateModel? = null
    var tripId = ""
    var selectedBoardingPoint by mutableStateOf<BoardingTime?>(null)
    var selectedDroppingPoint by mutableStateOf<DroppingTime?>(null)


    fun setTrips(availableTrip: AvailableTrip?) {
        _state.value = ScreenState(receivedResponse = availableTrip)
    }


    fun getBoardingDetails(context: Context) {
        val availableTrip = state.value.receivedResponse
        getBoardingDetailUseCase.getBoardingDetails(
            tripid = tripId,
            bpid = selectedBoardingPoint?.bpId ?: ""
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = ScreenState(isLoading = false, receivedResponse = availableTrip)
                    startBlockingTicket(context)
                }
                is Resource.Error -> {
                    context.showLongToast(it.message ?: "Some Failure, try again later")
                    _state.value = ScreenState(isLoading = false, receivedResponse = availableTrip)
                }
                is Resource.Loading -> {
                    _state.value = ScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun startBlockingTicket(context: Context) {
        val intent = Intent(context, BlockTicketActivity::class.java)
        intent.putExtra(Constant.SelectedTrip, state.value.receivedResponse)
        intent.putExtra(Constant.TripId, tripId)
        intent.putExtra(Constant.SelectedSeats, selectedSeats)
        intent.putExtra(Constant.BpId, selectedBoardingPoint?.bpId ?: "")
        intent.putExtra(Constant.DpId, selectedDroppingPoint?.bpId ?: "")
        intent.putExtra(Constant.FromCityModel, fromCityModel)
        intent.putExtra(Constant.ToCityModel, toCityModel)
        intent.putExtra(Constant.Date, date)
        context.startActivity(intent)
    }


}

enum class PageType {
    BOARDING,
    DROPPING
}
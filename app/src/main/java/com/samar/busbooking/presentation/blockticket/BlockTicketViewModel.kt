package com.samar.busbooking.presentation.blockticket

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.samar.busbooking.data.remote.BusApi
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.data.remote.dto.tripdetails.toInventoryMode
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.domain.model.inventory.InventoryMode
import com.samar.busbooking.domain.model.inventory.toInventoryItem
import com.samar.busbooking.domain.usecases.blockusecase.GetBlockUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.presentation.response.ResponseActivity
import com.samar.busbooking.util.Accessable
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Resource
import com.samar.busbooking.util.common.ViewUtils.showLongToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BlockTicketViewModel
@Inject constructor(private val getBlockUseCase: GetBlockUseCase) : BaseViewModel<List<InventoryMode>>() {

    var emailId by mutableStateOf("")
    var mobileNo by mutableStateOf("")
    var address by mutableStateOf("")

    var documentType = mutableStateOf("")
    var idNumber = mutableStateOf("")

    var trip: AvailableTrip? = null
    var selectedSeats = ArrayList<Seat>()
    var fromCityModel: CityModel? = null
    var toCityModel: CityModel? = null
    var date: DateModel? = null
    var tripId = ""
    var bpId = ""
    var dpId = ""


    fun setInventories() {
        val inventories = selectedSeats.map { r -> r.toInventoryMode() }
        inventories.first().passengerMode.primary.value = "1"
        _state.value = ScreenState(receivedResponse = inventories)
    }


    fun getTitleList(): List<String> = listOf("Mr", "Miss", "Mrs")

    fun getDocumentList(): List<String> = listOf("PAN", "AADHAR")


    private fun getValidation(): Boolean {
        try {
            if (emailId.trim().isEmpty() || mobileNo.trim().isEmpty() || documentType.value.trim()
                    .isEmpty() || idNumber.value.trim().isEmpty() || address.trim().isEmpty()
            ) {
                return false
            }
            _state.value.receivedResponse?.forEach {
                it.passengerMode.let { pass ->
                    if (pass.title.value.isEmpty() || pass.name.value.isEmpty() || pass.age.value.isEmpty() || pass.gender.value.isEmpty()) {
                        return false
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true
    }


    fun blockTheTicket(context: Context) {
        if (getValidation()) {
            if (Accessable.isAccessable()) {
                val oldData = _state.value.receivedResponse
                _state.value.receivedResponse?.let {inven->
                    val inventoryItem = inven.map { r->r.toInventoryItem() }
                    inventoryItem.first().passenger.email = emailId
                    inventoryItem.first().passenger.idNumber = idNumber.value
                    inventoryItem.first().passenger.idType = documentType.value
                    inventoryItem.first().passenger.mobile = mobileNo
                    inventoryItem.first().passenger.address = address

                    val ow = ObjectMapper().writer().withDefaultPrettyPrinter()
                    var json = ""
                    try {
                        json = ow.writeValueAsString(inventoryItem)
                    } catch (e: JsonProcessingException) {
                        e.printStackTrace()
                    }
                    getBlockUseCase.blockYourTicket(
                        aviltripid = tripId, boardingpid = bpId,
                        dpid = dpId, destid = toCityModel?.id?:"", arvialid = fromCityModel?.id?:"",
                        jsonpendata = json,
                        basefare = "",
                        totalFare = ""
                    ).onEach {
                        when (it) {
                            is Resource.Success -> {
                                val intent = Intent(context, ResponseActivity::class.java)
                                intent.putExtra(Constant.Response, it.data)
                                context.startActivity(intent)
                            }
                            is Resource.Error -> {
                                context.showLongToast(it.message?:"Some Error")
                                _state.value = ScreenState(receivedResponse = oldData)
                            }
                            is Resource.Loading -> {
                                _state.value = ScreenState(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
        } else {
            context.showLongToast("Enter all required fields")
        }
    }

}


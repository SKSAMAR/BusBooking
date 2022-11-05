package com.samar.busbooking.presentation.ticket

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.usecases.historyusecase.GetHistoryUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.presentation.response.ResponseActivity
import com.samar.busbooking.util.Accessable
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Resource
import com.samar.busbooking.util.common.ViewUtils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TicketViewModel
@Inject constructor(private val getHistoryUseCase: GetHistoryUseCase) :
    BaseViewModel<CheckTicket>() {

    var referenceId: String?= null
    private var cancelTicket = HashSet<String>()
    var cancelTickState by mutableStateOf(cancelTicket.isEmpty())

    fun setCheckTicket(ticket: CheckTicket) {
        _state.value = ScreenState(receivedResponse = ticket)
    }


    fun insertTicketsToBeCancelled(boolean: Boolean, seat: String) {
        if (boolean) {
            cancelTicket.add(seat)
            cancelTickState = cancelTicket.isEmpty()
        } else {
            cancelTicket.remove(seat)
            cancelTickState = cancelTicket.isEmpty()
        }
    }

    fun cancelTheTicket(context: Context) {
        if (Accessable.isAccessable()) {
            if (cancelTickState) {
                context.showToast("Select Seat Numbers to cancel")
            }
            else{
                val ow = ObjectMapper().writer().withDefaultPrettyPrinter()
                var json = ""
                try {
                    json = ow.writeValueAsString(cancelTicket.toList())
                } catch (e: JsonProcessingException) {
                    e.printStackTrace()
                }
                getHistoryUseCase.cancelTicket(referenceId?:"", json).onEach {
                    when (it) {
                        is Resource.Success -> {
                            val intent = Intent(context, ResponseActivity::class.java)
                            intent.putExtra(Constant.Response, it.data)
                            context.startActivity(intent)
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
        }
    }

}
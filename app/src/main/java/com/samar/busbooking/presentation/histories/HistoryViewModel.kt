package com.samar.busbooking.presentation.histories

import android.content.Context
import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.domain.usecases.historyusecase.GetHistoryUseCase
import com.samar.busbooking.presentation.common.BaseViewModel
import com.samar.busbooking.presentation.ticket.TicketActivity
import com.samar.busbooking.util.Accessable
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
@Inject constructor(private val getHistoryCase: GetHistoryUseCase) :
    BaseViewModel<List<HistoryData>>() {

    init {
        getHistories()
    }

    private fun getHistories() {
        getHistoryCase.invoke().onEach {
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


    fun checkTicket(referenceID: String, context: Context) {
        if (Accessable.isAccessable()) {
            val oldData = _state.value.receivedResponse
            getHistoryCase.checkTicket(referenceID)
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            val intent = Intent(context, TicketActivity::class.java)
                            intent.putExtra(Constant.CheckTicket, it.data)
                            intent.putExtra(Constant.referenceID, referenceID)
                            context.startActivity(intent)
                            _state.value = ScreenState(receivedResponse = oldData)
                        }
                        is Resource.Error -> {
                            _state.value = ScreenState(error = "Failed")
                        }
                        is Resource.Loading -> {
                            _state.value = ScreenState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }


}
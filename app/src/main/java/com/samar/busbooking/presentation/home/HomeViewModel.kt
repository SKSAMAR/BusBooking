package com.samar.busbooking.presentation.home

import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.presentation.common.BaseViewModel

class HomeViewModel: BaseViewModel<Any>() {

    init {
        _state.value = ScreenState(isLoading = true)
    }
}
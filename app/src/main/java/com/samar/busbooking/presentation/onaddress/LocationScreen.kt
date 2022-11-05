package com.samar.busbooking.presentation.onaddress

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.onaddress.component.AddressItem
import com.samar.busbooking.presentation.onaddress.component.TopBarCard
import com.samar.busbooking.util.common.sdp

@Composable
fun LocationScreen(
    viewModel: OnAddressViewModel = viewModel(),
    selected: (CityModel)-> Unit
) {
    BaseScaffold(
        topBar = {
            TopBarCard(hint = "${viewModel.pathUtil?.name?:""} Destination City", value = viewModel.searchLocation)
        },
        state = viewModel.state
    ) {
        val list = viewModel.getFilteredList()
        LazyColumn(modifier = Modifier.padding(horizontal = 5.sdp)){
            items(list) {
                AddressItem(cityModel = it, onClick = { selected(it) })
            }
        }
    }
}
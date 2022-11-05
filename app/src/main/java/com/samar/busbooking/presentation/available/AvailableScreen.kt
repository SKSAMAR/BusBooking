package com.samar.busbooking.presentation.available

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.available.component.AvailableBuses
import com.samar.busbooking.presentation.available.component.AvailableTopScreen
import com.samar.busbooking.presentation.available.component.BusSelectionCard
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.common.CustomSearchViewBasic
import com.samar.busbooking.util.common.sdp

@Composable
fun AvailableScreen(
    viewModel: AvailableViewModel = viewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getAvails()
    }

    BaseScaffold(
        topBar = {
            AvailableTopScreen()
        },
        state = viewModel.state,
        animationModel = viewModel.animationState
    ) {
        LazyColumn {
            item { AvailableBuses() }
            viewModel.state.value.receivedResponse?.let {
                items(it) { buses ->
                    val name = buses.travels ?: ""
                    if (name.contains(viewModel.search.value, true)) {
                        BusSelectionCard(
                            availableTrip = buses,
                            modifier = Modifier.padding(horizontal = 6.sdp, vertical = 2.sdp)
                        )
                    }
                }
            }

        }

    }

}
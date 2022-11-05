package com.samar.busbooking.presentation.tripdetails.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.samar.busbooking.presentation.common.ChipCard
import com.samar.busbooking.presentation.common.ChipCardPrices
import com.samar.busbooking.presentation.tripdetails.TripDetailViewModel
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.Constant.AllPrices
import com.samar.busbooking.util.common.sdp

@Composable
fun PriceRates(
    modifier: Modifier = Modifier,
    viewModel: TripDetailViewModel = viewModel()
) {
    val isSelectedItem: (String) -> Boolean = { viewModel.selectedPrice.value == it }
    val onChangeState: (String) -> Unit = { viewModel.selectedPrice.value = it }
    viewModel.getFaresList().let { boardingNameList ->
        if (boardingNameList.isNotEmpty()) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 10.sdp),
                crossAxisSpacing = 5.sdp,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
                mainAxisSpacing = 5.sdp
            ) {
                ChipCardPrices(text = AllPrices, onChangeState = onChangeState, isSelectedItem = isSelectedItem)
                boardingNameList.forEach { ChipCardPrices(text = it, onChangeState = onChangeState, isSelectedItem = isSelectedItem) }
            }
        }
    }
}
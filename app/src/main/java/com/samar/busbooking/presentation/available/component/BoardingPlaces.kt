package com.samar.busbooking.presentation.available.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.samar.busbooking.presentation.available.AvailableViewModel
import com.samar.busbooking.presentation.common.ChipCard
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun BoardingPlaces(
    modifier: Modifier = Modifier,
    viewModel: AvailableViewModel = viewModel()
) {
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    viewModel.boardingLocations().let { boardingNameList ->
        if (boardingNameList.isNotEmpty()) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(135.sdp)
                    .padding(10.sdp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.sdp),
                shape = RectangleShape
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.sdp, vertical = 5.sdp),
                        text = "Select Your Preferred Boarding Location",
                        fontSize = 12.textSdp,
                        fontWeight = FontWeight.SemiBold
                    )

                    FlowColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 10.sdp, vertical = 5.sdp),
                        crossAxisAlignment = FlowCrossAxisAlignment.Center,
                        crossAxisSpacing = 5.sdp,
                        mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
                        mainAxisSpacing = 5.sdp
                    ) {
                        boardingNameList.forEach { ChipCard(text = it, onChangeState = onChangeState, isSelectedItem = isSelectedItem) }
                    }
                }
            }
        }
    }
}
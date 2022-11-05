package com.samar.busbooking.presentation.tripdetails

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.tripdetails.component.*
import com.samar.busbooking.util.common.sdp

@Composable
fun TripDetailScreen(
    viewModel: TripDetailViewModel = viewModel()
){

    BaseScaffold(
        topBar = {
            TripDetailTopScreen()
        },
        bottomBar = {
            TripDetailBottomScreen()
        },
        state = viewModel.state,
        animationModel = viewModel.animationState
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            PriceRates()
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(10.sdp)){
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    SeatTypes(modifier = Modifier.width(180.sdp))
                    Spacer(modifier = Modifier.width(10.sdp))
                    SeatSelection(modifier = Modifier.width(210.sdp))

                }
            }
        }


    }

}
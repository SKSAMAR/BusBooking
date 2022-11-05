package com.samar.busbooking.presentation.tripdetails.component

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.samar.busbooking.R
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.data.remote.dto.tripdetails.getSeatType
import com.samar.busbooking.data.remote.dto.tripdetails.toSeatModel
import com.samar.busbooking.domain.model.seat.SeatModel
import com.samar.busbooking.presentation.tripdetails.TripDetailViewModel
import com.samar.busbooking.presentation.ui.theme.MyGreen
import com.samar.busbooking.util.common.sdp


@Composable
fun SeatSelection(
    modifier: Modifier = Modifier,
    tripDetailViewModel: TripDetailViewModel = viewModel()
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(2.sdp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.sdp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select your seats", modifier = Modifier
                    .padding(horizontal = 15.sdp, vertical = 10.sdp),
                fontWeight = FontWeight.SemiBold
            )
        }
        Divider(color = MaterialTheme.colorScheme.primary)
        val state = tripDetailViewModel.state.value
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.sdp, vertical = 10.sdp),
            mainAxisSpacing = 5.sdp,
            crossAxisSpacing = 5.sdp,
            mainAxisAlignment = MainAxisAlignment.Center
        ) {
            state.receivedResponse?.let { list ->
                list.forEach {
                    it.getSeatType()?.let { seatGender ->
                        SeatDataInfo(it, it.toSeatModel(), seatGender, baseFare = it.baseFare ?: "null")
                    }
                }
            }
        }
    }
}

@Composable
fun SeatDataInfo(
    seat: Seat,
    seatModel: SeatModel,
    seatGenderType: SeatGenderType,
    tripDetailViewModel: TripDetailViewModel = viewModel(),
    context: Context = LocalContext.current,
    baseFare: String
) {

    val modifier = if (seatGenderType == SeatGenderType.AvailableFemaleSeat ||
        seatGenderType == SeatGenderType.AvailableMaleSeat ||
        seatGenderType == SeatGenderType.AvailableSeat ||
        seatGenderType == SeatGenderType.AvailableFemaleSleeper ||
        seatGenderType == SeatGenderType.AvailableMaleSleeper ||
        seatGenderType == SeatGenderType.AvailableSleeper
    ) {
        Modifier.clickable {
            tripDetailViewModel.addSeats(context =  context, seat = seat, action = {
                seatModel.isSeatSelected.value = it
            })
        }
    }
    else{
        Modifier
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (seatGenderType == SeatGenderType.AvailableFemaleSeat ||
            seatGenderType == SeatGenderType.AvailableMaleSeat ||
            seatGenderType == SeatGenderType.AvailableSeat ||
            seatGenderType == SeatGenderType.AvailableFemaleSleeper ||
            seatGenderType == SeatGenderType.AvailableMaleSleeper ||
            seatGenderType == SeatGenderType.AvailableSleeper
        ) {
            if(seatModel.isSeatSelected.value){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    tint = MyGreen
                )
            }
            if (tripDetailViewModel.selectedPrice.value == baseFare) {
                Icon(
                    modifier = Modifier.size(14.sdp),
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "check",
                    tint = MyGreen
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = if (seatGenderType.type == SeatType.Seat) {
                    Modifier.size(40.sdp)
                } else {
                    Modifier
                        .width(40.dp)
                        .height(80.dp)
                },
                painter = painterResource(id = seatGenderType.icon),
                contentDescription = "available_seat",
            )
        }
    }
}

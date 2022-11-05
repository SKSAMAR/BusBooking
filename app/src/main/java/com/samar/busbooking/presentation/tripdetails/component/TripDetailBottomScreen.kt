package com.samar.busbooking.presentation.tripdetails.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.R
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.presentation.bodrop.BoDropActivity
import com.samar.busbooking.presentation.tripdetails.TripDetailActivity
import com.samar.busbooking.presentation.tripdetails.TripDetailViewModel
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun TripDetailBottomScreen(
    viewModel: TripDetailViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    if (viewModel.finalSeatsSelected.isNotEmpty()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.sdp, vertical = 10.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = "${viewModel.finalSeatsSelected.size} seats | ",
                        fontSize = 10.textSdp
                    )
                    viewModel.finalSeatsSelected.forEach {
                        Text(text = it.name + ", ", fontSize = 10.textSdp)
                    }
                }
                Text(
                    text = stringResource(id = R.string.rupee_sign) + " " + viewModel.getTotalBaseFare(),
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clickable {
                        val intent = Intent(context, BoDropActivity::class.java)
                        val arrayList = ArrayList<Seat>(viewModel.finalSeatsSelected)
                        intent.putExtra(Constant.SelectedSeats, arrayList)
                        intent.putExtra(Constant.SelectedTrip, viewModel.availableTrip)
                        intent.putExtra(Constant.TripId, viewModel.tripId)
                        intent.putExtra(Constant.FromCityModel, viewModel.fromCityModel)
                        intent.putExtra(Constant.ToCityModel, viewModel.toCityModel)
                        intent.putExtra(Constant.Date, viewModel.date)
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 12.sdp),
                    text = "SELECT BOARDING & DROPPING POINTS",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.textSdp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
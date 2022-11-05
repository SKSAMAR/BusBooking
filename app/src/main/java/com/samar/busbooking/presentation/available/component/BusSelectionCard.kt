package com.samar.busbooking.presentation.available.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.R
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.available.getBaseFares
import com.samar.busbooking.presentation.available.AvailableViewModel
import com.samar.busbooking.presentation.tripdetails.TripDetailActivity
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusSelectionCard(
    availableTrip: AvailableTrip?,
    modifier: Modifier = Modifier,
    availableViewModel: AvailableViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    Card(
        onClick = {
            val intent = Intent(context, TripDetailActivity::class.java)
            intent.putExtra(Constant.SelectedTrip, availableTrip)
            intent.putExtra(Constant.TripId, availableTrip?.id?:"")
            intent.putExtra(Constant.FromCityModel, availableViewModel.fromCityModel)
            intent.putExtra(Constant.ToCityModel, availableViewModel.toCityModel)
            intent.putExtra(Constant.Date, availableViewModel.date)
            context.startActivity(intent)
        },
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
        ) {
            Text(
                text = "Starts From",
                color = MaterialTheme.colorScheme.error,
                fontSize = 10.textSdp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 2.sdp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                availableTrip?.boardingTimes?.forEach { times ->
                    Text(
                        text = times?.location ?: "",
                        fontSize = 8.textSdp,
                        modifier = Modifier.padding(end = 1.sdp)
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "dest",
                        modifier = Modifier
                            .size(8.sdp)
                            .padding(end = 1.sdp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.padding(top = 2.sdp)) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("" + availableTrip?.departureTime?.timeFromMinutesToActual()?.hours + ":" + availableTrip?.departureTime?.timeFromMinutesToActual()?.minutes)
                            }

                            append(" - " + availableTrip?.arrivalTime?.timeFromMinutesToActual()?.hours + ":" + availableTrip?.arrivalTime?.timeFromMinutesToActual()?.minutes)
                        },
                        fontSize = 12.textSdp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = Color.Gray)) {
                                append(availableTrip?.duration + " • ")
                            }
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 8.textSdp
                                )
                            ) {
                                append(availableTrip?.availableSingleSeat + " Seats")
                            }
                        },
                        fontSize = 10.textSdp
                    )
                }
                availableTrip?.getBaseFares()?.let {
                    Text(
                        text = stringResource(id = R.string.rupee_sign) + it,
                        fontSize = 14.textSdp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.sdp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Icon(
                            imageVector = Icons.Default.DirectionsBus,
                            contentDescription = "bus",
                            modifier = Modifier
                                .size(12.sdp)
                                .padding(end = 2.sdp)
                        )

                        Text(
                            text = availableTrip?.travels ?: "",
                            fontSize = 12.textSdp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        modifier = Modifier.padding(vertical = 2.sdp),
                        text = availableTrip?.busType ?: "",
                        fontSize = 8.textSdp,
                    )
                }
            }
        }
        Divider()
    }
}
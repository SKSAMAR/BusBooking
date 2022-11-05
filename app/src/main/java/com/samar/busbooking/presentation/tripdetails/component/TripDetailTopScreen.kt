package com.samar.busbooking.presentation.tripdetails.component

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.available.AvailableViewModel
import com.samar.busbooking.presentation.tripdetails.TripDetailViewModel
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailTopScreen(
    viewModel: TripDetailViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = viewModel.availableTrip?.travels?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${viewModel.fromCityModel?.name} - ${viewModel.toCityModel?.name}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Text(
                        text = "${viewModel.date?.englishData} ${viewModel.date?.day} ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("" + viewModel.availableTrip?.departureTime?.timeFromMinutesToActual()?.hours + ":" + viewModel.availableTrip?.departureTime?.timeFromMinutesToActual()?.minutes)
                            }
                            append(" - " + viewModel.availableTrip?.arrivalTime?.timeFromMinutesToActual()?.hours + ":" + viewModel.availableTrip?.arrivalTime?.timeFromMinutesToActual()?.minutes)
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    val activity = context as ComponentActivity
                    activity.onBackPressedDispatcher.onBackPressed()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.surface
        )
    )


}
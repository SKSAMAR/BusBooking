package com.samar.busbooking.presentation.ticket

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.common.SeatSelectChip
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    viewModel: TicketViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Ticket Details")
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
                            contentDescription = "back"
                        )
                    }
                }
            )
        },
        state = viewModel.state
    ) {
        viewModel.state.value.receivedResponse?.data.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                it?.inventoryItems?.let {
                    if (it.isNotEmpty()) {
                        Card(
                            modifier = Modifier
                                .padding(12.sdp)
                                .fillMaxWidth()
                        ) {

                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.sdp, vertical = 10.sdp),
                                crossAxisSpacing = 5.sdp,
                                mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
                                mainAxisSpacing = 5.sdp
                            ) {
                                Card(
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.cancelTheTicket(context)
                                        },
                                    shape = RoundedCornerShape(4.sdp),
                                    border = BorderStroke(width = .4.dp, color = MaterialTheme.colorScheme.inverseSurface),
                                    colors = if (!viewModel.cancelTickState) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) else CardDefaults.cardColors()
                                ) {
                                    Text(
                                        modifier = Modifier.padding(horizontal = 15.sdp, vertical = 7.sdp),
                                        text = "Cancel",
                                        fontSize = 10.textSdp,
                                        fontWeight = FontWeight.ExtraBold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                it.forEach {
                                    SeatSelectChip(
                                        text = it.seatName ?: "",
                                        onChangeState = { seat, action ->
                                            viewModel.insertTicketsToBeCancelled(action, seat)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(12.sdp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.sdp)
                    ) {
                        Text(
                            text = it.toString() + "\n\n" + it?.inventoryItems?.first()
                                .toString() + "\n\n" + it?.inventoryItems?.first()?.passenger?.toString()
                        )
                    }
                }
            }
        }
    }
}
package com.samar.busbooking.presentation.histories.component

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
import com.samar.busbooking.data.remote.dto.history.HistoryData
import com.samar.busbooking.presentation.available.AvailableViewModel
import com.samar.busbooking.presentation.histories.HistoryViewModel
import com.samar.busbooking.presentation.tripdetails.TripDetailActivity
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorySelectionCard(
    historyData: HistoryData,
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    Card(
        onClick = {
            viewModel.checkTicket(historyData.rEFID?:"", context)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(10.sdp),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Amount: ${historyData.aMOUNT}",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 10.textSdp
                )
                Text(
                    text = "${historyData.sTATUS}",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 10.textSdp
                )
            }
            Column(modifier = Modifier.padding(top = 2.sdp)) {
                Text(
                    text = "Reference ID: ${historyData.rEFID}",
                    fontSize = 12.textSdp
                )
                Text(
                    text = "UTR ID: ${historyData.rESPONSE?.data?.utr}",
                    fontSize = 12.textSdp
                )
                Text(
                    text = "Blocked ID: ${historyData.rESPONSE?.data?.blockKey}",
                    fontSize = 12.textSdp
                )
                Text(
                    text = "Date : ${historyData.dATE}",
                    fontSize = 12.textSdp
                )

            }
        }
        Divider()
    }
}
package com.samar.busbooking.presentation.available.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.EventSeat
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.samar.busbooking.presentation.available.AvailableViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Preview(showBackground = true)
@Composable
fun AvailableBuses(
    viewModel: AvailableViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    viewModel.getTotalBusesNumber()?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.sdp, horizontal = 5.sdp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.EventAvailable, contentDescription = "bus")
                Text(
                    text = "$it Total buses found",
                    modifier = Modifier.padding(vertical = 2.sdp, horizontal = 5.sdp),
                    fontSize = 10.textSdp,
                )
            }

            Row(
                modifier = Modifier.padding(vertical = 2.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val seats = viewModel.getTotalAvailableSeats()
                Icon(imageVector = Icons.Outlined.EventSeat, contentDescription = "bus")
                Text(
                    text = "$seats Available",
                    modifier = Modifier.padding(vertical = 10.sdp, horizontal = 5.sdp),
                    fontSize = 10.textSdp,
                )
            }


        }
    }
}
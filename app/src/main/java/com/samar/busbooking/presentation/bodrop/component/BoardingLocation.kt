package com.samar.busbooking.presentation.bodrop.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.samar.busbooking.data.remote.dto.available.BoardingTime
import com.samar.busbooking.data.remote.dto.available.DroppingTime
import com.samar.busbooking.util.common.ViewUtils.timeFromMinutesToActual
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun BoardingLocation(
    boardingTime: BoardingTime?,
    isSelectedBoardingItem: (BoardingTime) -> Boolean,
    onChangeBoardingState: (BoardingTime) -> Unit

) {
    boardingTime?.let { boarding ->
        Column(
            modifier = Modifier
                .selectable(
                    selected = isSelectedBoardingItem(boarding),
                    onClick = { onChangeBoardingState(boarding) },
                    role = Role.RadioButton
                )
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.sdp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            top = 5.sdp,
                            bottom = 5.sdp,
                            start = 10.sdp,
                            end = 2.sdp
                        )
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelectedBoardingItem(boarding),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.inversePrimary
                        )
                    )
                    Column(modifier = Modifier.padding(start = 5.sdp)) {
                        Text(text = ""+boarding.location, fontWeight = FontWeight.SemiBold, fontSize = 12.textSdp)
                        Text(text = ""+boarding.address, fontSize = 10.textSdp)
                    }
                }

                Text(
                    text = ""+boarding.time?.timeFromMinutesToActual()?.hours + ":" + boarding.time?.timeFromMinutesToActual()?.minutes,
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            top = 5.sdp,
                            bottom = 5.sdp,
                            start = 2.sdp,
                            end = 10.sdp
                        )
                )
            }
        }
    }
}

@Composable
fun DropingLocation(
    droppingTime: DroppingTime?,
    isSelectedDroppingItem: (DroppingTime) -> Boolean,
    onChangeDroppingState: (DroppingTime) -> Unit

) {
    droppingTime?.let { droping ->
        Column(
            modifier = Modifier
                .selectable(
                    selected = isSelectedDroppingItem(droping),
                    onClick = { onChangeDroppingState(droping) },
                    role = Role.RadioButton
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.sdp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            top = 5.sdp,
                            bottom = 5.sdp,
                            start = 10.sdp,
                            end = 2.sdp
                        )
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelectedDroppingItem(droping),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.inversePrimary
                        )
                    )
                    Column(modifier = Modifier.padding(start = 5.sdp)) {
                        Text(text = ""+droping.location, fontWeight = FontWeight.SemiBold, fontSize = 12.textSdp)
                        Text(text = ""+droping.address, fontSize = 10.textSdp)
                    }
                }

                Text(
                    text = ""+droping.time?.timeFromMinutesToActual()?.hours + ":" + droping.time?.timeFromMinutesToActual()?.minutes,
                    fontSize = 12.textSdp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            top = 5.sdp,
                            bottom = 5.sdp,
                            start = 2.sdp,
                            end = 10.sdp
                        )
                )
            }
        }
    }
}
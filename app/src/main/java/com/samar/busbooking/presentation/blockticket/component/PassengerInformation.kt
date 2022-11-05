package com.samar.busbooking.presentation.blockticket.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.domain.model.inventory.InventoryMode
import com.samar.busbooking.presentation.blockticket.BlockTicketViewModel
import com.samar.busbooking.presentation.common.Dropdown
import com.samar.busbooking.presentation.tripdetails.component.getPossibleGender
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerInformation(
    viewModel: BlockTicketViewModel = viewModel(),
    inventoryMode: InventoryMode,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp, vertical = 2.sdp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Text(
                text = if (inventoryMode.passengerMode.primary.value == "1") "Primary Passenger" else "Co Passenger",
                fontSize = 10.textSdp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 2.sdp, horizontal = 10.sdp)
            )

            Column(modifier = Modifier.padding(vertical = 10.sdp, horizontal = 10.sdp)) {

                Dropdown(
                    modifier = Modifier.fillMaxWidth(),
                    titles = viewModel.getTitleList(),
                    hint = "Title",
                    value = inventoryMode.passengerMode.title,
                    onChange = { inventoryMode.passengerMode.title.value = it }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = inventoryMode.passengerMode.name.value,
                    onValueChange = {
                        inventoryMode.passengerMode.name.value = it
                    },
                    label = {
                        Text(text = "Name")
                    },
                    singleLine = true,
                    maxLines = 1
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = inventoryMode.passengerMode.age.value,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            if (it.length < 11) {
                                inventoryMode.passengerMode.age.value = it
                            }
                        }
                    },
                    label = {
                        Text(text = "Age")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.sdp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Gender", fontSize = 10.textSdp)
                    Spacer(modifier = Modifier.width(10.sdp))
                    if (inventoryMode.genderSelection != null) {

                        val isSelectedItem: (String) -> Boolean =
                            { inventoryMode.passengerMode.gender.value == it }
                        val onChangeState: (String) -> Unit =
                            { inventoryMode.passengerMode.gender.value = it }
                        inventoryMode.genderSelection.let {
                            it.getPossibleGender().forEach {
                                RadioButton(
                                    modifier = Modifier.size(10.sdp),
                                    selected = isSelectedItem(it),
                                    onClick = {
                                        onChangeState(it)
                                    }
                                )
                                Text(
                                    text = it,
                                    fontSize = 10.textSdp,
                                    modifier = Modifier.padding(horizontal = 10.sdp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.sdp))

            }
        }


    }
}
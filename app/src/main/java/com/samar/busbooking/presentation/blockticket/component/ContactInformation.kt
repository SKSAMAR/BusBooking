package com.samar.busbooking.presentation.blockticket.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import com.samar.busbooking.presentation.blockticket.BlockTicketViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.common.Dropdown
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInformation(
    viewModel: BlockTicketViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.sdp)
    ) {
        Text(
            text = "Contact Information",
            fontSize = 10.textSdp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 2.sdp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Column(modifier = Modifier.padding(vertical = 10.sdp, horizontal = 10.sdp)) {

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.emailId,
                    onValueChange = {
                        viewModel.emailId = it
                    },
                    label = {
                        Text(text = "Email")
                    },
                    singleLine = true,
                    maxLines = 1
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.address,
                    onValueChange = {
                        viewModel.address = it
                    },
                    label = {
                        Text(text = "Address")
                    },
                    singleLine = true,
                    maxLines = 1
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.mobileNo,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            if (it.length < 11) {
                                viewModel.mobileNo = it
                            }
                        }
                    },
                    label = {
                        Text(text = "Mobile")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    maxLines = 1
                )
                Dropdown(
                    titles = viewModel.getDocumentList(),
                    hint = "Document Type",
                    value = viewModel.documentType,
                    onChange = { viewModel.documentType.value = it }
                )
                if (viewModel.documentType.value.isNotEmpty()){
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.idNumber.value,
                        onValueChange = {
                            viewModel.idNumber.value = it
                        },
                        label = {
                            Text(text = viewModel.documentType.value+" Number")
                        },
                        singleLine = true,
                        maxLines = 1
                    )
                }


            }
        }


    }
}
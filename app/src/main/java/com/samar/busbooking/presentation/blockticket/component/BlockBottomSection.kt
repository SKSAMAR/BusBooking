package com.samar.busbooking.presentation.blockticket.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.samar.busbooking.presentation.blockticket.BlockTicketViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun BlockBottomSection(
    viewModel: BlockTicketViewModel = viewModel(),
    context: Context = LocalContext.current
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable {
                viewModel.blockTheTicket(context)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.sdp),
            text = "BlOCK THIS TICKET",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 12.textSdp,
            color = MaterialTheme.colorScheme.surface
        )
    }
}
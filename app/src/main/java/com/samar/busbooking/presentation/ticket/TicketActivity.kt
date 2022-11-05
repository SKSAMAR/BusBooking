package com.samar.busbooking.presentation.ticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samar.busbooking.data.remote.dto.ticket.checkticket.CheckTicket
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import com.samar.busbooking.util.common.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketActivity : ComponentActivity() {

    val viewModel by viewModels<TicketViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInitializer()
        setContent {
            BusBookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketScreen()
                }
            }
        }
    }


    private fun doInitializer() {
        viewModel.referenceId = intent.getStringExtra(Constant.referenceID)
        val ticket = intent.getSerializableExtra(Constant.CheckTicket) as CheckTicket
        viewModel.setCheckTicket(ticket)
    }

}
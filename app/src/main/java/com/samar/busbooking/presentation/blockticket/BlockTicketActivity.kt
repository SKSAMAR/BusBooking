package com.samar.busbooking.presentation.blockticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samar.busbooking.data.remote.dto.available.AvailableTrip
import com.samar.busbooking.data.remote.dto.tripdetails.Seat
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import com.samar.busbooking.util.common.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlockTicketActivity : ComponentActivity() {

    val viewModel by viewModels<BlockTicketViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInitializers()
        setContent {
            BusBookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BlockTicketScreen()
                }
            }
        }
    }


    private fun doInitializers() {
        viewModel.trip = intent.getSerializableExtra(Constant.SelectedTrip) as AvailableTrip?
        viewModel.selectedSeats =
            intent.getSerializableExtra(Constant.SelectedSeats) as ArrayList<Seat>
        viewModel.fromCityModel = intent.getSerializableExtra(Constant.FromCityModel) as CityModel?
        viewModel.toCityModel = intent.getSerializableExtra(Constant.ToCityModel) as CityModel?
        viewModel.date = intent.getSerializableExtra(Constant.Date) as DateModel?
        viewModel.tripId = intent.getStringExtra(Constant.TripId) ?: ""
        viewModel.bpId = intent.getStringExtra(Constant.BpId) ?: ""
        viewModel.dpId = intent.getStringExtra(Constant.DpId) ?: ""
        viewModel.setInventories()
    }

}
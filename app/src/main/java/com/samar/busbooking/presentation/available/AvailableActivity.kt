package com.samar.busbooking.presentation.available

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samar.busbooking.domain.model.DateModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import com.samar.busbooking.util.common.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableActivity: ComponentActivity() {

    private val viewModel by viewModels<AvailableViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startProcessing()
        setContent {
            BusBookingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AvailableScreen()
                }
            }
        }
    }
    private fun startProcessing(){
        viewModel.fromCityModel = intent.getSerializableExtra(Constant.FromCityModel) as CityModel?
        viewModel.toCityModel = intent.getSerializableExtra(Constant.ToCityModel) as CityModel?
        viewModel.date = intent.getSerializableExtra(Constant.Date) as DateModel?
    }
}
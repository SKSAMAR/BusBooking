package com.samar.busbooking.presentation.onaddress

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.preferences.PathUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationSelectionActivity: ComponentActivity() {

    private val viewModel by viewModels<OnAddressViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.pathUtil = intent.getSerializableExtra(Constant.TypeAddress) as PathUtil
        setContent {
            BusBookingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationScreen(selected = {
                        revertBackTheData(it)
                    })
                }
            }
        }
    }

    private fun revertBackTheData(cityModel: CityModel){
        val intentData = Intent()
        intentData.putExtra(Constant.CityModel, cityModel)
        setResult(RESULT_OK, intentData)
        finish()
    }

}
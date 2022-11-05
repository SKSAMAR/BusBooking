package com.samar.busbooking.presentation.histories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.samar.busbooking.presentation.ui.theme.BusBookingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity: ComponentActivity() {

    val viewModel by viewModels<HistoryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusBookingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HistoryScreen()
                }
            }
        }
    }



}
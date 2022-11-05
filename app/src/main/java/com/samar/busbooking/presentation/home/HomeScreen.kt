package com.samar.busbooking.presentation.home

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.histories.HistoryActivity
import com.samar.busbooking.presentation.home.component.BookingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Buses")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            context.startActivity(Intent(context, HistoryActivity::class.java))
                        }) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = "history"
                        )
                    }
                }
            )
        },
        state = viewModel.state,
        animationModel = viewModel.animationState
    ) {
        BookingCard()
    }
}
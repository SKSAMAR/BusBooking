package com.samar.busbooking.presentation.histories

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.presentation.histories.component.HistorySelectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Histories")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val activity = context as ComponentActivity
                        activity.onBackPressedDispatcher.onBackPressed()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                }
            )
        },
        state = viewModel.state
    ) {
        viewModel.state.value.receivedResponse?.let {histories->
            LazyColumn{
                items(histories){history->
                    HistorySelectionCard(historyData = history)
                }
            }
        }
    }
}
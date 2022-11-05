package com.samar.busbooking.presentation.blockticket

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.blockticket.component.BlockBottomSection
import com.samar.busbooking.presentation.blockticket.component.ContactInformation
import com.samar.busbooking.presentation.blockticket.component.PassengerInformation
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlockTicketScreen(
    viewModel: BlockTicketViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = viewModel.fromCityModel?.name + " to " + viewModel.toCityModel?.name,
                        fontSize = 14.textSdp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        val activity = context as ComponentActivity
                        activity.onBackPressedDispatcher.onBackPressed()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
                )
            )
        },
        bottomBar = {
            BlockBottomSection()
        },
        state = viewModel.state
    ) {
        viewModel.state.value.receivedResponse?.let { inventories ->
            LazyColumn {
                item {
                    ContactInformation()
                }
                item {
                    Text(
                        text = "Contact Information",
                        fontSize = 10.textSdp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 5.sdp, horizontal = 10.sdp)
                    )
                }
                items(inventories) { data ->
                    PassengerInformation(viewModel = viewModel, inventoryMode = data)
                }
            }
        }
    }
}
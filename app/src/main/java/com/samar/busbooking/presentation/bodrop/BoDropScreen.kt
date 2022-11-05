package com.samar.busbooking.presentation.bodrop

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.data.remote.dto.available.BoardingTime
import com.samar.busbooking.data.remote.dto.available.DroppingTime
import com.samar.busbooking.presentation.bodrop.component.BoDropTopScreen
import com.samar.busbooking.presentation.bodrop.component.BoardingLocation
import com.samar.busbooking.presentation.bodrop.component.DropingLocation
import com.samar.busbooking.presentation.common.BaseScaffold
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoDropScreen(
    viewModel: BoDropViewModel = viewModel(),
    context: Context = LocalContext.current,
) {

    val isSelectedBoardingItem: (BoardingTime) -> Boolean = { viewModel.selectedBoardingPoint == it }
    val onChangeBoardingState: (BoardingTime) -> Unit = { viewModel.selectedBoardingPoint = it }

    val isSelectedDropingItem: (DroppingTime) -> Boolean = { viewModel.selectedDroppingPoint == it }
    val onChangeDropingState: (DroppingTime) -> Unit = { viewModel.selectedDroppingPoint = it }

    BaseScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = viewModel.fromCityModel?.name + " to " + viewModel.toCityModel?.name, fontSize = 14.textSdp)
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
                actions = {
                    Text(text = "Next")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            if (viewModel.selectedBoardingPoint!= null && viewModel.selectedDroppingPoint!=null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                        .clickable {
                            viewModel.getBoardingDetails(context)
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 12.sdp),
                        text = "PROCEED AHEAD FURTHER",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.textSdp,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        },
        state = viewModel.state,
        animationModel = viewModel.animationState
    ) {
        BoDropTopScreen()
        Divider()
        viewModel.state.value.receivedResponse?.let {trip->
            LazyColumn {
                if(viewModel.pageType == PageType.BOARDING){
                    trip.boardingTimes?.let {boardingTimes->
                        items(boardingTimes){item->
                            BoardingLocation(
                                boardingTime = item,
                                isSelectedBoardingItem = isSelectedBoardingItem ,
                                onChangeBoardingState = onChangeBoardingState
                            )
                        }
                    }
                }
                else{
                    trip.droppingTimes?.let {droppingTimes->
                        items(droppingTimes){item->
                            DropingLocation(
                                droppingTime = item,
                                isSelectedDroppingItem = isSelectedDropingItem,
                                onChangeDroppingState = onChangeDropingState
                            )
                        }
                    }
                }
            }

        }

    }


}
package com.samar.busbooking.presentation.bodrop.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.samar.busbooking.presentation.bodrop.BoDropViewModel
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.bodrop.PageType

@Composable
fun BoDropTopScreen(
    modifier: Modifier = Modifier,
    viewModel: BoDropViewModel = viewModel()
){

    Row(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .clickable {
                viewModel.pageType = PageType.BOARDING
            }
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 5.sdp,
                            horizontal = 10.sdp
                        )
                ) {
                    Text(
                        text = "Boarding", fontSize = 17.textSdp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 2.sdp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = viewModel.selectedBoardingPoint?.location?:"Add Location",
                        fontSize = 12.textSdp,
                        modifier = Modifier.padding(vertical = 2.sdp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                val state = MutableTransitionState(viewModel.pageType == PageType.BOARDING)
                AnimatedVisibility(visibleState = state) {
                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 3.sdp)
                }
                if (state.currentState){
                    Divider()
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .clickable {
                viewModel.pageType = PageType.DROPPING
            }
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 5.sdp,
                            horizontal = 10.sdp
                        )
                ) {
                    Text(
                        text = "Dropping", fontSize = 17.textSdp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 2.sdp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = viewModel.selectedDroppingPoint?.location?:"Add Location",
                        fontSize = 12.textSdp,
                        modifier = Modifier.padding(vertical = 2.sdp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                val state = MutableTransitionState(viewModel.pageType == PageType.DROPPING)
                AnimatedVisibility(visibleState = state) {
                    Divider(color = MaterialTheme.colorScheme.primary, thickness = 3.sdp)
                }
                if (state.currentState){
                    Divider()
                }
            }
        }
    }
}



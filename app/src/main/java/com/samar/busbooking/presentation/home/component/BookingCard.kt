package com.samar.busbooking.presentation.home.component

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.presentation.common.DateDialogSys.showDateDialog
import com.samar.busbooking.presentation.home.HomeViewModel
import com.samar.busbooking.presentation.onaddress.LocationSelectionActivity
import com.samar.busbooking.util.common.Constant
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp
import com.samar.busbooking.util.dates.OnDate
import com.samar.busbooking.util.dates.allDayBadges
import com.samar.busbooking.util.preferences.PathUtil
import com.samar.busbooking.util.preferences.preferenceList

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BookingCard(
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val selectedValue = remember { mutableStateOf(allDayBadges.first()) }
    val isSelectedItem: (OnDate) -> Boolean = { selectedValue.value == it }
    val onChangeState: (OnDate) -> Unit = { selectedValue.value = it }

    val fromLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK){
            viewModel.fromLocation.value = it.data?.getSerializableExtra(Constant.CityModel) as CityModel?
            viewModel.fromLocation.value?.let {model->
                viewModel.setFromLocation(model)
            }
        }
    }

    val toLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK){
            viewModel.toLocation.value = it.data?.getSerializableExtra(Constant.CityModel) as CityModel?
            viewModel.toLocation.value?.let {model->
                viewModel.setToLocation(model)
            }
        }
    }

    val constrainSet = ConstraintSet {
        val fullCard = createRefFor("fullCard")
        val searchButton = createRefFor("searchButton")

        constrain(fullCard) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(searchButton) {
            top.linkTo(fullCard.bottom)
            bottom.linkTo(fullCard.bottom)
            end.linkTo(fullCard.end)
            start.linkTo(fullCard.start)
        }

    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.sdp)
            .aspectRatio(1f),
        constraintSet = constrainSet
    ) {
        Card(
            modifier = modifier
                .layoutId("fullCard")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.padding(10.sdp)) {

                    Row {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .clickable {
                                    val intent = Intent(context, LocationSelectionActivity::class.java)
                                    intent.putExtra(Constant.TypeAddress, PathUtil.FROM)
                                    fromLauncher.launch(intent)
                                }
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 2.sdp),
                                text = "From",
                                fontSize = 12.textSdp,
                                color = Color.Gray
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.sdp),
                                text = viewModel.fromLocation.value?.name?:"Enter City",
                                fontSize = 14.textSdp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .clickable {
                                    val intent = Intent(context, LocationSelectionActivity::class.java)
                                    intent.putExtra(Constant.TypeAddress, PathUtil.TO)
                                    toLauncher.launch(intent)
                                }
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(vertical = 2.sdp),
                                text = "To",
                                fontSize = 12.textSdp,
                                color = Color.Gray
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.sdp),
                                text = viewModel.toLocation.value?.name?:"Enter City",
                                fontSize = 13.textSdp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.End,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    IconButton(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            viewModel.swapAddresses()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChangeCircle,
                            contentDescription = "exchange"
                        )
                    }
                }
                Divider()
                Row(
                    modifier = Modifier.padding(10.sdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.2f)
                            .clickable {
                                context.showDateDialog { viewModel.toSpecificDates(it) }
                            }
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 2.sdp),
                            text = "On",
                            fontSize = 12.textSdp,
                            color = Color.Gray
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.sdp),
                            text = "" + viewModel.dateModel.englishData,
                            fontSize = 16.textSdp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 2.sdp),
                            text = "" + viewModel.dateModel.day,
                            fontSize = 12.textSdp,
                            color = Color.Gray
                        )

                    }

                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        columns = GridCells.Fixed(1),
                    ) {
                        items(allDayBadges) {
                            RadioBadges(
                                onDate = it,
                                isSelectedItem = isSelectedItem,
                                onChangeState = onChangeState,
                                onClick = { onDate ->
                                    viewModel.changeDate(onDate)
                                }
                            )
                        }
                    }
                }
                Divider()
                Column {
                    Text(
                        modifier = Modifier.padding(vertical = 2.sdp, horizontal = 10.sdp),
                        text = "Preference",
                        fontSize = 12.textSdp,
                        color = Color.Gray
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        preferenceList.forEach { pref ->
                            PreferenceItem(busPreferences = pref)
                        }
                    }
                }
            }
        }
        Card(
            modifier = Modifier.layoutId("searchButton"),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            onClick = {
                viewModel.searchTheBus(context)
            }
        ) {
            Text(
                text = "Search Buses",
                modifier = Modifier.padding(horizontal = 25.sdp, vertical = 10.sdp)
            )
        }

    }
}
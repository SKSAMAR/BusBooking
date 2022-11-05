package com.samar.busbooking.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samar.busbooking.presentation.home.HomeViewModel
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp
import com.samar.busbooking.util.preferences.BusPreferences

@Composable
fun PreferenceItem(
    modifier: Modifier = Modifier,
    busPreferences: BusPreferences,
    viewModel: HomeViewModel = viewModel()
) {

    Column(
        modifier = modifier
            .padding(horizontal = 5.sdp, vertical = 5.sdp)
            .clickable {
                busPreferences.preferenceModel.isSelected.value = !busPreferences.preferenceModel.isSelected.value
                viewModel.addToPreference(busPreferences)
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = busPreferences.preferenceModel.icon,
            contentDescription = busPreferences.preferenceModel.title,
            tint = if (busPreferences.preferenceModel.isSelected.value) MaterialTheme.colorScheme.primary else Color.Gray
        )

        Text(
            modifier = Modifier.padding(vertical = 5.sdp),
            text = busPreferences.preferenceModel.title,
            fontSize = 12.textSdp,
            color = if (busPreferences.preferenceModel.isSelected.value) MaterialTheme.colorScheme.primary else Color.Gray
        )

    }

}
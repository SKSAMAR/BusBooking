package com.samar.busbooking.presentation.onaddress.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.samar.busbooking.domain.model.cities.CityModel
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun AddressItem(
    modifier: Modifier = Modifier,
    cityModel: CityModel,
    onClick:(CityModel)->Unit
){
    Column( modifier = Modifier.clickable { onClick(cityModel) } ) {
        Row(
            modifier = modifier
                .padding(horizontal = 1.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 5.sdp),
                imageVector = Icons.Default.LocationCity,
                contentDescription = "locationCity"
            )
            Text(text = cityModel.name, fontSize = 14.textSdp, modifier = Modifier.padding(vertical = 12.sdp))
        }
        Divider()
    }
}
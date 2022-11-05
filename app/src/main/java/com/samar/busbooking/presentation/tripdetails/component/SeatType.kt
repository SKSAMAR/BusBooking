package com.samar.busbooking.presentation.tripdetails.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samar.busbooking.R
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp


@Composable
fun SeatTypes(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(2.sdp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.sdp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seat Types", modifier = Modifier
                    .padding(horizontal = 15.sdp, vertical = 10.sdp),
                fontWeight = FontWeight.SemiBold
            )
        }
        Divider(color = MaterialTheme.colorScheme.primary)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.sdp, vertical = 10.sdp),
            horizontalAlignment = Alignment.Start
        ) {

            SeatData(SeatGenderType.AvailableSeat)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.AvailableFemaleSeat)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.AvailableMaleSeat)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedSeat)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedMaleSeat)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedFemaleSeat)
            Spacer(modifier = Modifier.height(5.sdp))


            SeatData(SeatGenderType.AvailableSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.AvailableFemaleSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.AvailableMaleSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedMaleSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
            SeatData(SeatGenderType.BlockedFemaleSleeper)
            Spacer(modifier = Modifier.height(5.sdp))
        }
    }
}

@Composable
fun SeatData(
    seatGenderType: SeatGenderType
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val modifier = if (seatGenderType.type == SeatType.Seat){
            Modifier.size(40.sdp)
        }
        else{
            Modifier
                .width(40.dp)
                .height(80.dp)
        }
        Image(
            modifier = modifier,
            painter = painterResource(id = seatGenderType.icon),
            contentDescription = "available_seat",
        )
        Spacer(modifier = Modifier.width(5.sdp))
        Text(text = seatGenderType.info, fontSize = 10.textSdp)
    }
}

enum class SeatType{
    Seat,
    Sleeper
}

sealed class SeatGenderType(val icon: Int, val info: String, val type: SeatType){
    object AvailableSeat : SeatGenderType(icon = R.drawable.available_bus_seat_all, "Available Seat", SeatType.Seat)
    object BlockedSeat : SeatGenderType(icon = R.drawable.blocked_bus_seat_all, "Blocked Seat", SeatType.Seat)
    object AvailableFemaleSeat : SeatGenderType(icon = R.drawable.available_bus_seat_female, "Available for female Seat", SeatType.Seat)
    object BlockedFemaleSeat : SeatGenderType(icon = R.drawable.blocked_bus_seat_female, "Blocked by female Seat", SeatType.Seat)
    object AvailableMaleSeat : SeatGenderType(icon = R.drawable.available_bus_seat_male, "Available for male Seat", SeatType.Seat)
    object BlockedMaleSeat : SeatGenderType(icon = R.drawable.blocked_bus_seat_male, "Blocked by male Seat", SeatType.Seat)


    object AvailableSleeper : SeatGenderType(icon = R.drawable.available_bus_sleeper, "Available Sleeper", SeatType.Sleeper)
    object BlockedSleeper : SeatGenderType(icon = R.drawable.blocked_bus_sleeper, "Blocked Sleeper", SeatType.Sleeper)
    object AvailableFemaleSleeper : SeatGenderType(icon = R.drawable.available_bus_sleaper_female, "Available for female Sleeper", SeatType.Sleeper)
    object BlockedFemaleSleeper : SeatGenderType(icon = R.drawable.blocked_bus_sleeper_female, "Blocked by female Sleeper", SeatType.Sleeper)
    object AvailableMaleSleeper : SeatGenderType(icon = R.drawable.available_bus_sleeper_male, "Available for male Sleeper", SeatType.Sleeper)
    object BlockedMaleSleeper : SeatGenderType(icon = R.drawable.blocked_bus_sleeper_male, "Blocked by male Sleeper", SeatType.Sleeper)

}


fun SeatGenderType.getPossibleGender(): List<String>{
    when(this){
        is SeatGenderType.AvailableSeat->{
            return listOf("Male", "Female")
        }
        is SeatGenderType.AvailableSleeper->{
            return listOf("Male", "Female")
        }
        is SeatGenderType.AvailableFemaleSeat->{
            return listOf("Female")
        }
        is SeatGenderType.AvailableFemaleSleeper->{
            return listOf("Female")
        }
        is SeatGenderType.AvailableMaleSeat->{
            return listOf("Male")
        }
        is SeatGenderType.AvailableMaleSleeper->{
            return listOf("Male")
        }
        else -> {
            return listOf("Male", "Female")
        }
    }
}
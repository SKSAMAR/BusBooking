package com.samar.busbooking.presentation.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp
import com.samar.busbooking.util.dates.OnDate

@Composable
fun RadioBadges(
    modifier: Modifier = Modifier,
    onDate: OnDate = OnDate.Today,
    isSelectedItem: (OnDate) -> Boolean,
    onChangeState: (OnDate) -> Unit,
    onClick: (OnDate) -> Unit
) {
    Card(
        modifier = modifier
            .padding(top = 1.sdp, bottom = 1.sdp, end = 1.sdp)
            .clip(RoundedCornerShape(2.sdp))
            .selectable(
                selected = isSelectedItem(onDate),
                onClick = {
                    onChangeState(onDate)
                    onClick(onDate)
                },
                role = Role.RadioButton
            ),
        shape = RoundedCornerShape(2.sdp),
        colors = CardDefaults.cardColors(containerColor = if (isSelectedItem(onDate)) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.inverseSurface),
        elevation = CardDefaults.cardElevation(if (isSelectedItem(onDate)) 4.sdp else 1.sdp),
    ) {
        Text(
            modifier = Modifier
                .padding(1.sdp)
                .align(Alignment.CenterHorizontally),
            text = onDate.name,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.textSdp
        )
    }
}
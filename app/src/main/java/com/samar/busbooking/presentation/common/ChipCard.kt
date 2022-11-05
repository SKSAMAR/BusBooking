package com.samar.busbooking.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.samar.busbooking.R
import com.samar.busbooking.util.common.sdp
import com.samar.busbooking.util.common.textSdp

@Composable
fun ChipCard(
    modifier: Modifier = Modifier,
    text: String,
    isSelectedItem: (String) -> Boolean,
    onChangeState: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(4.sdp),
        border = BorderStroke(width = .4.dp, color = MaterialTheme.colorScheme.inverseSurface),
        colors = if (isSelectedItem(text)) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) else CardDefaults.cardColors()
    ) {
        Text(
            modifier = modifier
                .padding(vertical = 10.sdp, horizontal = 7.sdp)
                .selectable(
                    selected = isSelectedItem(text),
                    onClick = {
                        onChangeState(text)
                    },
                    role = Role.RadioButton
                ),
            text = "" + text,
            fontSize = 10.textSdp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ChipCardPrices(
    modifier: Modifier = Modifier,
    text: String,
    isSelectedItem: (String) -> Boolean,
    onChangeState: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(4.sdp),
        border = BorderStroke(width = .4.dp, color = MaterialTheme.colorScheme.inverseSurface),
        colors = if (isSelectedItem(text)) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) else CardDefaults.cardColors()
    ) {
        Text(
            modifier = modifier
                .padding(vertical = 5.sdp, horizontal = 7.sdp)
                .selectable(
                    selected = isSelectedItem(text),
                    onClick = {
                        onChangeState(text)
                    },
                    role = Role.RadioButton
                ),
            text = stringResource(id = R.string.rupee_sign) + text,
            fontSize = 10.textSdp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SeatSelectChip(
    modifier: Modifier = Modifier,
    text: String,
    onChangeState: (String, Boolean) -> Unit
) {
    var isSelected by remember{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .clickable {
                isSelected = !isSelected
                onChangeState(text, isSelected)
            },
        shape = RoundedCornerShape(4.sdp),
        border = BorderStroke(width = .4.dp, color = MaterialTheme.colorScheme.inverseSurface),
        colors = if (isSelected) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) else CardDefaults.cardColors()
    ) {
        Text(
            modifier = modifier
                .padding(horizontal = 15.sdp, vertical = 7.sdp),
            text = ""+ text,
            fontSize = 10.textSdp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
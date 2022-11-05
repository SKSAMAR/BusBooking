package com.samar.busbooking.presentation.onaddress.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.samar.busbooking.presentation.common.CustomTextField
import com.samar.busbooking.util.common.sdp

@Composable
fun TopBarCard(
    modifier: Modifier = Modifier,
    hint: String,
    value: MutableState<String>,
    exitAction:()->Unit = {}
){

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RectangleShape
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 6.sdp)
                .padding(vertical = 6.sdp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        ) {

            CustomTextField(
                modifier = Modifier.padding(vertical = 12.sdp, horizontal = 5.sdp),
                text = value,
                placeholderText = hint,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(end = 10.sdp).clickable { exitAction() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "exit"
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { value.value = "" },
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear"
                    )
                }
            )
            /**
            val colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        androidx.compose.material.MaterialTheme.shapes.small,
                    ),
                value = value.value,
                onValueChange = { value.value = it },
                placeholder = {
                    Text(text = hint)
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.clickable { exitAction() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "exit"
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { value.value = "" },
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear"
                    )
                },
                colors = colors,
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                ),
                singleLine = true,
                maxLines = 1,
            )
            **/
        }
    }
}
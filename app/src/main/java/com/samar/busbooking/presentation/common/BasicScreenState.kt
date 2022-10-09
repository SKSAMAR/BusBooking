package com.samar.busbooking.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.samar.busbooking.domain.model.ScreenState
import com.samar.busbooking.util.common.textSdp
import com.samar.busbooking.R

@Composable
fun<T> BasicScreenState(
    modifier: Modifier = Modifier,
    state: State<ScreenState<T>>,
    content: @Composable ColumnScope.() -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.value.isLoading){
            ScreenAnimation(
                resources = R.raw.loading
            )
        }
        else if(state.value.error.trim().isNotEmpty()){

           Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Top,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               ScreenAnimation(
                   modifier = Modifier.fillMaxSize(.3f)
                       .clip(RectangleShape),
                   resources = R.raw.uh_oh
               )
               Text(
                   modifier = Modifier.fillMaxWidth(),
                   text = state.value.error,
                   color = MaterialTheme.colorScheme.error,
                   fontSize = 18.textSdp,
                   style = TextStyle(textAlign = TextAlign.Center),
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )
           }
        }
        else{
            Column(
                modifier = modifier,
                content = content
            )
        }
    }
}